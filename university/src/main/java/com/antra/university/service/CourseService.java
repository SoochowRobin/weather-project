package com.antra.university.service;

import com.antra.university.pojo.Course;
import com.antra.university.pojo.Student;
import com.antra.university.pojo.dto.StudentInfoDTO;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Optional<List<Course>> findAll();

    Optional<Course> findById(int id);

    int save(Course course);

    void update(int id, Course course);

    void deleteById(int id);

    Optional<List<StudentInfoDTO>> findStuByCourseId(int id);
}
