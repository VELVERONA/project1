package com.ngoc.project1.controller;

import com.ngoc.project1.dto.CourseDTO;
import com.ngoc.project1.entity.Course;
import com.ngoc.project1.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/admin/add-course")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        return  ResponseEntity.ok(courseService.addCourse(courseDTO));
    }

    @GetMapping("/adminuser/get-all-courses")
    public ResponseEntity<CourseDTO> getAllCourses() {
        return  ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/adminuser/get-course-id/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return  ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PutMapping("/admin/update-course/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id,@RequestBody Course course) {
        return  ResponseEntity.ok(courseService.updateCourse(id,course));
    }

    @DeleteMapping("/admin/deleteCourse/{id}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable Long id) {
        return  ResponseEntity.ok(courseService.deleteCourse(id));
    }
}
