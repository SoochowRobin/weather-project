package com.example.search.controller;

import com.example.search.pojo.dto.PersonListDTO;
import com.example.search.pojo.dto.StuCourseDTO;
import com.example.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails() throws ExecutionException, InterruptedException {

        CompletableFuture<StuCourseDTO> stuCourseDTO = searchService.searchAll();
        CompletableFuture<PersonListDTO> personListDTO = searchService.searchProvider();

        Map<String, Object> allInfo = new HashMap<>();
        StuCourseDTO stuCourseDTO1 = stuCourseDTO.get();
        PersonListDTO personListDTO1 = personListDTO.get();

        allInfo.put("stuCourse", stuCourseDTO1);
        allInfo.put("person", personListDTO1);

        return new ResponseEntity<>(allInfo, HttpStatus.OK);
    }




    /**
     *
     * Test code for specific endpoint

    @GetMapping("/students/courses")
    public ResponseEntity<StuCourseDTO> getAll(){
        Optional<StuCourseDTO> stuCourseDTO = searchService.searchAll();
        return new ResponseEntity(stuCourseDTO, HttpStatus.OK);
    }



    @GetMapping("/person")
    public ResponseEntity<PersonListDTO> getPerson(){
        Optional<PersonListDTO> personListDTO = searchService.searchProvider();
        return new ResponseEntity<>(personListDTO.orElse(null), HttpStatus.OK);
    }

    */

}
