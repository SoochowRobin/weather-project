package com.example.search.service.impl;

import com.example.search.pojo.dto.PersonListDTO;
import com.example.search.pojo.dto.StuCourseDTO;
import com.example.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchServiceImpl implements SearchService {

    private RestTemplate restTemplate;

    @Autowired
    public SearchServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    @Async("taskExecutor")
    public CompletableFuture<PersonListDTO> searchProvider() {
        String url = "http://provider-service/person";
        List forObject = restTemplate.getForObject(url, List.class); // 这边用的List
        return CompletableFuture.completedFuture(new PersonListDTO(forObject));
    }


    // 为什这边用Map才有用？？ 用自己定义的dto没用
    // ResponseEntity<StuCourseDTO> stuCourseInfo = restTemplate.getForEntity(url, StuCourseDTO.class);

    // 为什么加上 @Async 就出现nullpointerException, 或者直接返还 null
    @Override
    @Async("taskExecutor")
    public CompletableFuture<StuCourseDTO> searchAll() {
        String url = "http://university-service/students/courses"; // should be the service you registered
        ResponseEntity<Map> stuCourseInfo = restTemplate.getForEntity(url, Map.class);
        return CompletableFuture.completedFuture(new StuCourseDTO(stuCourseInfo.getBody()));
    }


}
