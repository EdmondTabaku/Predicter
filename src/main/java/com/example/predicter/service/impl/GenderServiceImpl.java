package com.example.predicter.service.impl;

import com.example.predicter.exception.InvalidRequestException;
import com.example.predicter.model.Gender;
import com.example.predicter.service.GenderService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenderServiceImpl implements GenderService {

    private final RestTemplate restTemplate;

    public GenderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String findByName(String name) {
        String uri = "https://api.genderize.io?name=" + name;

        if (name == null){
            throw new InvalidRequestException("Name is invalid");
        }

        Gender gender = restTemplate.getForObject(uri, Gender.class);

        if ( gender.getGender() != null ){
            return gender.getGender();
        }else {
            throw new InvalidRequestException("Name is invalid");
        }
    }
}
