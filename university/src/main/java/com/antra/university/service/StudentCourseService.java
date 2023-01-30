package com.antra.university.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentCourseService {

    double findByStuIdCourseId(int stuId, int courseId);

    Optional<Map<String, List<String>>> findAll();

    int insert(int stuId, int courseId, double score);

    void update(int stuId, int courseId, double score);

    void deleteByStuIdCourseId(int stuId, int courseId);


}
