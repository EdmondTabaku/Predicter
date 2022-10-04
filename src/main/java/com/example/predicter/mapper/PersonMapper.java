package com.example.predicter.mapper;

import com.example.predicter.dto.PersonDto;
import com.example.predicter.model.Person;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto toPersonDto(Person person);
}
