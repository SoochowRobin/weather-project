package com.antra.university.service.impl;

import com.antra.university.pojo.Course;
import com.antra.university.pojo.Student;
import com.antra.university.pojo.Student_Course;
import com.antra.university.pojo.dto.CourseInfoDTO;
import com.antra.university.repository.StudentRepository;
import com.antra.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<List<Student>> findAll() {
//         use Optional.ofNullable() instead of of()
        return Optional.ofNullable(studentRepository.findAll());
    }

    @Override
    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<List<CourseInfoDTO>> findCoursesByStuId(int id) {
        Optional<Student> student = findById(id);
        if(student.orElse(null) == null){
            throw new RuntimeException("Student does not find");
        }
        // get student_course list
        List<Student_Course> student_course = student.get().getStudent_course();
        List<CourseInfoDTO> courses = new ArrayList<>();

        for(Student_Course sc: student_course){
            courses.add(new CourseInfoDTO(sc.getCourse().getName(), sc.getCourse().getDescription()));
        }
        return Optional.ofNullable(courses);
    }


    @Override
    @Transactional
    public int save(Student student) {
        Student save = studentRepository.save(student);
        return save.getId();
    }

    @Override
    @Transactional
    public void update(int id, Student student) {
        Optional<Student> stu = studentRepository.findById(id);
        Student toBeUpdated = stu.orElse(null);
        if(toBeUpdated == null){
            // TODO: NEED TO CUSTOMIZED EXCEPTION
            throw new RuntimeException("Student does not exist!");
        }
        // saveAndFlush() must set entity id first
        student.setId(id);
        studentRepository.saveAndFlush(student);
    }

    @Override
    @Transactional
    public void delete(int id) {

        // deleteById(): If the entity is not found in the persistence store it is silently ignored.
        // so i decide to find it first if null, report it to controller
        Optional<Student> stu = studentRepository.findById(id);
        if(stu.orElse(null) == null){
            // TODO: NEED TO CUSTOMIZED EXCEPTION
            throw new RuntimeException("Student does not exist!");
        }
        // delete
        studentRepository.deleteById(id);
    }
}
