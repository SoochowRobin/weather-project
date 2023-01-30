package com.antra.provider.repository;

import com.antra.provider.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//use jpa interface
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
