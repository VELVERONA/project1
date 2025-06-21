package com.ngoc.project1.repository;

import com.ngoc.project1.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CourseRepo extends JpaRepository<Course, Long> {


}
