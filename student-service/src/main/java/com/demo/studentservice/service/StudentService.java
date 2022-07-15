package com.demo.studentservice.service;

import com.demo.studentservice.entity.Student;
import com.demo.studentservice.repository.StudentRepository;
import com.demo.studentservice.feignclients.SubjectFeignClient;
import com.demo.studentservice.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SubjectFeignClient subjectFeignClient;


    public List<Student> getAll() {

        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {

        return studentRepository.findById(id).orElse(null);
    }

    public Student save(Student student) {
        Student student1 = studentRepository.save(student);
        return student1;
    }

    public List<Subject> getSubject(int studentId) {
        List<Subject> subjects = restTemplate.getForObject("http://subject-service/subject/byuser/" + studentId, List.class);
        return subjects;
    }


    public Subject saveSubject(int studentId, Subject subject) {
        subject.setStudentId(studentId);
        Subject subjectNew = subjectFeignClient.save(subject);
        return subjectNew;
    }




}
