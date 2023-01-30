package com.antra.provider.service;

import com.antra.provider.pojo.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    int create(Person person);
}
