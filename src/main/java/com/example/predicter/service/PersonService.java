package com.example.predicter.service;

import com.example.predicter.dto.PersonDto;

public interface PersonService {

    public PersonDto findByName(String name);
    public PersonDto delete(Integer id);
}
