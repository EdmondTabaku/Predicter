package com.example.predicter.service.impl;

import com.example.predicter.dto.PersonDto;
import com.example.predicter.exception.InvalidRequestException;
import com.example.predicter.mapper.PersonMapper;
import com.example.predicter.model.Person;
import com.example.predicter.repository.PersonRepository;
import com.example.predicter.service.AgeService;
import com.example.predicter.service.GenderService;
import com.example.predicter.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final AgeService ageService;
    private final GenderService genderService;
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonServiceImpl(AgeService ageService, GenderService genderService, PersonRepository personRepository, PersonMapper personMapper) {
        this.ageService = ageService;
        this.genderService = genderService;
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }


    @Override
    public PersonDto findByName(String name) {

        Optional<Person> personOptional = personRepository.findByName(name);
        if (personOptional.isPresent()){
            System.out.println("Person with name " + personOptional.get().getName() + " was found on db");
            return personMapper.toPersonDto(personOptional.get());
        }

        Person person = new Person();

        person.setName(name);
        person.setAge(ageService.findByName(name));
        person.setGender(genderService.findByName(name));

        if (person.getName() != null && person.getAge() != null && person.getGender() != null){
            personRepository.save(person);
        }

        System.out.println("Person with name " + person.getName() + " was generated from the api");
        return personMapper.toPersonDto(person);
    }

    @Override
    public PersonDto delete(Integer id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()){
            personRepository.deleteById(id);
            return personMapper.toPersonDto(personOptional.get());
        }else {
            throw new InvalidRequestException("Person with id " + id + " does not exist");
        }
    }
}











