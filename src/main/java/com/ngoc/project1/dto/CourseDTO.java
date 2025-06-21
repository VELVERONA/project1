package com.ngoc.project1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ngoc.project1.entity.Course;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDTO {
    private String courseName;
    private String courseDescription;
    private Double coursePrice;
    private String error;
    private String message;
    private Integer StatusCode;
    private Course course;
    private List<Course> courseList;

}
