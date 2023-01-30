package com.antra.university.controller;

import com.antra.university.pojo.Course;
import com.antra.university.pojo.Student;
import com.antra.university.pojo.dto.StudentInfoDTO;
import com.antra.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAll(){
        return new ResponseEntity<>(courseService.findAll().orElse(null), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable int id){
        return new ResponseEntity<>(courseService.findById(id).orElse(null), HttpStatus.OK);
    }

    // TODO: 有套娃现象
    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentInfoDTO>> findStuByCourseId(@PathVariable int id){
        return new ResponseEntity<>(courseService.findStuByCourseId(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody Course course){
        return new ResponseEntity<>(courseService.save(course), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody Course course){
        courseService.update(id, course);
        // return 204
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        courseService.deleteById(id);
        // return 204
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
