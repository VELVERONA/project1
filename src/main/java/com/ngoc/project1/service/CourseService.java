package com.ngoc.project1.service;

import com.ngoc.project1.dto.CourseDTO;
import com.ngoc.project1.entity.Course;
import com.ngoc.project1.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public CourseDTO addCourse(CourseDTO courseDTO) {
        CourseDTO CDTO = new CourseDTO();
        try{
            Course course = new Course();
            course.setCourseName(courseDTO.getCourseName());
            course.setCourseDescription(courseDTO.getCourseDescription());
            course.setPrice(courseDTO.getCoursePrice());
            Course Ourcourse = courseRepo.save(course);
            if (Ourcourse.getCourseId() > 0) {
                CDTO.setCourse((Ourcourse));
                CDTO.setMessage("Course added successfully");
                CDTO.setStatusCode(200);
            }
        }
        catch(Exception e){
            CDTO.setStatusCode(500);
            CDTO.setError(e.getMessage());
        }
        return CDTO;
    }

    public CourseDTO getAllCourses() {
        CourseDTO courseDTO = new CourseDTO();
        try {
            List<Course> courses = courseRepo.findAll();
            if(!courses.isEmpty()){
                courseDTO.setCourseList(courses);
                courseDTO.setStatusCode(200);
                courseDTO.setMessage("All courses successfully");
            }
            else{
                courseDTO.setStatusCode(404);
                courseDTO.setError("Course not found");
            }

        }
        catch(Exception e){
            courseDTO.setStatusCode(500);
            courseDTO.setError(e.getMessage());
        }
        return courseDTO;
    }
    public CourseDTO getCourseById(Long id){
        CourseDTO courseDTO = new CourseDTO();
        try{
            Course course = courseRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
            courseDTO.setCourse(course);
            courseDTO.setStatusCode(200);
            courseDTO.setMessage("Users with id '" + id + "' found");

        } catch (Exception e){
            courseDTO.setStatusCode(500);
            courseDTO.setMessage("Error occured: " + e.getMessage());
        }
        return courseDTO;
    }
    public CourseDTO updateCourse(Long id, Course updateCourse) {
        CourseDTO courseDTO = new CourseDTO();
        try {
            Optional<Course> course = courseRepo.findById(id);
            if(course.isPresent()){
                Course existingCourse = course.get();
                existingCourse.setCourseName(updateCourse.getCourseName());
                existingCourse.setCourseDescription(updateCourse.getCourseDescription());
                existingCourse.setPrice(updateCourse.getPrice());
                Course savedCourse = courseRepo.save(existingCourse);
                courseDTO.setCourse(savedCourse);
                courseDTO.setStatusCode(200);
                courseDTO.setMessage("Course updated successfully");
            }

        } catch (Exception e){
            courseDTO.setStatusCode(500);
            courseDTO.setError(e.getMessage());
            courseDTO.setMessage("Error occured: " + e.getMessage());
        }
        return courseDTO;
    }

    public CourseDTO deleteCourse(Long id) {
        CourseDTO courseDTO = new CourseDTO();
        try{
            Optional<Course> course = courseRepo.findById(id);
            if(course.isPresent()){
                courseRepo.deleteById(id);
                courseDTO.setStatusCode(200);
                courseDTO.setMessage("Course deleted successfully");
            }
            else{
                courseDTO.setStatusCode(404);
                courseDTO.setError("Course not found");
            }

        }
        catch(Exception e){
            courseDTO.setStatusCode(500);
            courseDTO.setError(e.getMessage());
        }
        return courseDTO;
    }
}
