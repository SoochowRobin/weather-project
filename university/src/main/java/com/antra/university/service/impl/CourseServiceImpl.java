package com.antra.university.service.impl;

import com.antra.university.pojo.Course;
import com.antra.university.pojo.Student;
import com.antra.university.pojo.Student_Course;
import com.antra.university.pojo.dto.StudentInfoDTO;
import com.antra.university.repository.CourseRepository;
import com.antra.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<List<Course>> findAll() {
        return Optional.ofNullable(courseRepository.findAll());
    }

    @Override
    public Optional<Course> findById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public int save(Course course) {
        Course savedCourse = courseRepository.save(course);
        return savedCourse.getId();
    }

    @Override
    @Transactional
    public void update(int id, Course course) {
        Optional<Course> courseFind = courseRepository.findById(id);
        Course course1 = courseFind.orElse(null);
        if(course1 == null){
            // TODO: ADD CUSTOMIZED EXCEPTION HERE
            throw new RuntimeException("Course does not exist");
        }
        course.setId(id);
        courseRepository.saveAndFlush(course);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Optional<Course> courseFind = courseRepository.findById(id);
        if(courseFind.orElse(null) == null){
            throw new RuntimeException("Course does not exist");
        }
        // delete
        courseRepository.deleteById(id);
    }


    @Override
    public Optional<List<StudentInfoDTO>> findStuByCourseId(int id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.orElse(null) == null){
            throw new RuntimeException("course does not exists");
        }

        List<Student_Course> student_course = course.get().getStudent_course();
        List<StudentInfoDTO> students = new ArrayList<>();
        for (Student_Course sc: student_course){
            students.add(new StudentInfoDTO(sc.getStudent().getName(), sc.getStudent().getGrade(), sc.getStudent().getGpa()));
        }
        return Optional.ofNullable(students);
    }
}
