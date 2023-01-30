package com.antra.provider.service.impl;

import com.antra.provider.pojo.Person;
import com.antra.provider.repository.PersonRepository;
import com.antra.provider.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    @Override
    public int create(Person person) {
        Person savedPerson = personRepository.save(person);
        return savedPerson.getId();
    }
}
