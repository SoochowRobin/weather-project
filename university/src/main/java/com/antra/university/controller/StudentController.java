package com.antra.university.controller;

import com.antra.university.pojo.Course;
import com.antra.university.pojo.Student;
import com.antra.university.pojo.dto.CourseInfoDTO;
import com.antra.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll(){
        return new ResponseEntity<>(studentService.findAll().orElse(null), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable int id){
        return new ResponseEntity<>(studentService.findById(id).orElse(null), HttpStatus.OK);
    }

    // TODO: 会有套完现象
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseInfoDTO>> findCoursesById(@PathVariable int id){
        return new ResponseEntity<>(studentService.findCoursesByStuId(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody Student student){
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody Student student){
        studentService.update(id, student);
        // HttpStatus 204: no content
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        studentService.delete(id);
        // return 204
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
