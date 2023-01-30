package com.antra.university.repository;

import com.antra.university.pojo.Student_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentCourseRepository extends JpaRepository<Student_Course, Integer> {

    @Query("select sc from Student_Course sc where sc.student.id = ?1 and sc.course.id = ?2")
    Optional<Student_Course> findByStuCourse(int stuId, int courseId);



}
