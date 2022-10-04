package com.example.predicter.service.impl;

import com.example.predicter.exception.InvalidRequestException;
import com.example.predicter.model.Age;
import com.example.predicter.service.AgeService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AgeServiceImpl implements AgeService {

    private final RestTemplate restTemplate;

    public AgeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     *
     * A method to find a person's age by his name
     *
     * @param name - the name of person
     * @return Integer - Age
     */
    @Override
    public Integer findByName(String name) {
        String uri = "https://api.agify.io?name=" + name;

        if (name == null){
            throw new InvalidRequestException("Name is invalid");
        }

        Age age = restTemplate.getForObject(uri, Age.class);
        if ( age.getAge() != null ){
            return age.getAge();
        }else {
            throw new InvalidRequestException("Name is invalid");
        }


    }
}
