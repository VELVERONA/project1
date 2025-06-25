package com.ngoc.project1.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int CourseId;
    private String CourseName;
    private String CourseDescription;
    private Double Price;
    
}
