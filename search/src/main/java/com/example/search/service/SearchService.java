package com.example.search.service;

import com.example.search.pojo.Course;
import com.example.search.pojo.Person;
import com.example.search.pojo.Student;
import com.example.search.pojo.dto.PersonListDTO;
import com.example.search.pojo.dto.StuCourseDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface SearchService {

    CompletableFuture<PersonListDTO> searchProvider();

    CompletableFuture<StuCourseDTO> searchAll();
}
