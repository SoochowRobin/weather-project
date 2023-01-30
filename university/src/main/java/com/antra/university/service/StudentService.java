package com.antra.university.service;

import com.antra.university.pojo.Course;
import com.antra.university.pojo.Student;
import com.antra.university.pojo.dto.CourseInfoDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<List<Student>> findAll();

    Optional<Student> findById(int id);

    Optional<List<CourseInfoDTO>> findCoursesByStuId(int id);

    int save(Student student);

    void update(int id, Student student);

    void delete(int id);
}
