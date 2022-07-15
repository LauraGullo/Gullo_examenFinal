package com.demo.studentservice.controller;

import com.demo.studentservice.entity.Student;
import com.demo.studentservice.model.Subject;
import com.demo.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> users = studentService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") int id) {
        Student user = studentService.getStudentById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student user) {
        Student userNew = studentService.save(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/subjects/{studentId}")
    public ResponseEntity<List<Subject>> getSubject(@PathVariable("studentId") int studentId) {
        Student student = studentService.getStudentById(studentId);
        if(student == null)
            return ResponseEntity.notFound().build();
        List<Subject> subjects = studentService.getSubject(studentId);
        return ResponseEntity.ok(subjects);
    }



    @PostMapping("/savesubject/{studentId}")
    public ResponseEntity<Subject> saveSubject(@PathVariable("studentId") int studentId, @RequestBody Subject subject) {
        if(studentService.getStudentById(studentId) == null)
            return ResponseEntity.notFound().build();
        Subject subject1 = studentService.saveSubject(studentId, subject);
        return ResponseEntity.ok(subject);
    }



}
