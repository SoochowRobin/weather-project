package com.antra.university.controller;

import com.antra.university.pojo.dto.ScoreDTO;
import com.antra.university.pojo.dto.StudentCourseDTO;
import com.antra.university.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentCourseController {

    private final StudentCourseService scService;

    @Autowired
    public StudentCourseController(StudentCourseService scService) {
        this.scService = scService;
    }

    @GetMapping("/students/{sId}/courses/{cId}")
    public ResponseEntity<Double> find(@PathVariable(name = "sId") int stuId, @PathVariable("cId") int courseId){
        return new ResponseEntity<>(scService.findByStuIdCourseId(stuId, courseId), HttpStatus.OK);
    }

    // TODO: 感觉不应该返回 Student_Course对象，应该返回map
    @GetMapping("/students/courses")
    public ResponseEntity<Map<String, List<String>>> findAll(){
        return new ResponseEntity<>(scService.findAll().orElse(null), HttpStatus.OK);
    }


    @PostMapping("/students/courses")
    public ResponseEntity<Integer> save(@RequestBody StudentCourseDTO scDTO){
        return new ResponseEntity<>(scService.insert(scDTO.getStuId(), scDTO.getCourseId(), scDTO.getScore()), HttpStatus.CREATED);
    }

    @PutMapping("/students/{sId}/courses/{cId}")
    public ResponseEntity update(@PathVariable int sId, @PathVariable int cId, @RequestBody ScoreDTO scoreDTO){
        scService.update(sId, cId, scoreDTO.getScore());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/students/{sId}/courses/{cId}")
    public ResponseEntity delete(@PathVariable int sId, @PathVariable int cId){
        scService.deleteByStuIdCourseId(sId, cId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
