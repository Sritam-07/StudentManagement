package com.example.StudentManagement.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String course;

    private String name;

    private Long age;

    private String studentClass;

//    public void setId(Long id){
//        this.id=id;
//    }
//
//    public void setCourse(String course){
//        this.course=course;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getCourse() {
//        return course;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Long getAge() {
//        return age;
//    }
//
//    public void setAge(Long age) {
//        this.age = age;
//    }
//
//    public String getStudentClass() {
//        return studentClass;
//    }
//
//    public void setStudentClass(String studentClass) {
//        this.studentClass = studentClass;
//    }
//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", course='" + course + '\'' +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", studentClass='" + studentClass + '\'' +
//                '}';
//    }
}
