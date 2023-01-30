package com.antra.university.service.impl;

import com.antra.university.pojo.Course;
import com.antra.university.pojo.Student;
import com.antra.university.pojo.Student_Course;
import com.antra.university.repository.CourseRepository;
import com.antra.university.repository.StudentCourseRepository;
import com.antra.university.repository.StudentRepository;
import com.antra.university.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentCourseRepository scRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentCourseServiceImpl(StudentCourseRepository scRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.scRepository = scRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public double findByStuIdCourseId(int stuId, int courseId) {
        Optional<Student_Course> sc = scRepository.findByStuCourse(stuId, courseId);
        // TODO: ADD EXCEPTION
        if(sc.orElse(null) == null){
            throw new RuntimeException("The score does not exist!");
        }
        return sc.get().getScore();
    }


    // TODO: 有bug,出现套娃的现象
    // 应该返回一个map: Map<Map<String>>, 但是这样设计也不合理， stuId，作为key的话不能重复， stuId -> List<courseId>?
    // 但是这样的话，course也应该有对应。 所以这个many-to-many的关系怎么设计？
    // 是应该在这里也搞一个 asych?
    @Override
    public Optional<Map<String, List<String>>> findAll() {

        List<Student_Course> all = scRepository.findAll();
        Map<String, List<String>> stuCourseInfo = new HashMap<>();
        for(Student_Course sc: all){
            String stuName= sc.getStudent().getName();
            List<String> courseInfo = stuCourseInfo.getOrDefault(stuName, new ArrayList<>());
            courseInfo.add(sc.getCourse().getName());
            stuCourseInfo.put(stuName, courseInfo);
        }
        return Optional.ofNullable(stuCourseInfo);
    }



    @Override
    public int insert(int stuId, int courseId, double score) {
        Optional<Student> student = studentRepository.findById(stuId);
        Optional<Course> course = courseRepository.findById(courseId);

        if(student.orElse(null) == null){
            throw new RuntimeException("Student does not exist!");
        }

        if(course.orElse(null) == null){
            throw new RuntimeException("Course does not exist!");
        }

        Student_Course sc = new Student_Course();
        sc.setStudent(student.get());
        sc.setCourse(course.get());
        sc.setScore(score);
        Student_Course savedSC = scRepository.save(sc);
        return savedSC.getId();
    }

    @Override
    public void update(int stuId, int courseId, double score) {
        Optional<Student_Course> student_course = scRepository.findByStuCourse(stuId, courseId);
        if(student_course.orElse(null) == null){
            throw new RuntimeException("student_course does not exist!");
        }

        Student_Course sc = student_course.get();
        sc.setScore(score);
        scRepository.saveAndFlush(sc);
    }

    @Override
    public void deleteByStuIdCourseId(int stuId, int courseId) {
        Optional<Student_Course> sc = scRepository.findByStuCourse(stuId, courseId);
        if(sc.orElse(null) == null){
            throw new RuntimeException("This score does not exist!");
        }
        scRepository.delete(sc.get());
    }
}
