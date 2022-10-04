package com.example.predicter.controller;

import com.example.predicter.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping(value = {"/{name}", "/"})
    public ResponseEntity<?> findByName(@PathVariable(name = "name", required = false) String name){
        return ResponseEntity.ok(personService.findByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(personService.delete(id));
    }
}
