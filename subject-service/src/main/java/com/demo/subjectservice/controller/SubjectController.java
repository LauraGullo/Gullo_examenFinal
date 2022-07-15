package com.demo.subjectservice.controller;

import com.demo.subjectservice.entity.Subject;
import com.demo.subjectservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Subject>> getAll() {
        List<Subject> subjects = subjectService.getAll();
        if(subjects.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Subject> getById(@PathVariable("id") int id) {
        Subject subject = subjectService.getSubjectById(id);
        if(subject == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(subject);
    }

    @PostMapping("/save")
    public ResponseEntity<Subject> save(@RequestBody Subject subject) {
        Subject subjectNew = subjectService.save(subject);
        return ResponseEntity.ok(subjectNew);
    }

    @GetMapping("/byStudent/{userId}")
    public ResponseEntity<List<Subject>> getByStudentId(@PathVariable("studentId") int studentId) {
        List<Subject> subjects = subjectService.byStudentId(studentId);
        return ResponseEntity.ok(subjects);
    }

}
