package com.antra.provider.controller;


import com.antra.provider.pojo.Person;
import com.antra.provider.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController("/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll(){
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody Person person){
        int id = personService.create(person);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
