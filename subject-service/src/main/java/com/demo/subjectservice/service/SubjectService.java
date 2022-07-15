package com.demo.subjectservice.service;

import com.demo.subjectservice.entity.Subject;
import com.demo.subjectservice.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public List<Subject> getAll() {

        return subjectRepository.findAll();
    }

    public Subject getSubjectById(int id) {

        return subjectRepository.findById(id).orElse(null);
    }

    public Subject save(Subject subject) {
        Subject subjectNew = subjectRepository.save(subject);
        return subjectNew;
    }

    public List<Subject> byStudentId(int studentId) {

        return subjectRepository.findByStudentId(studentId);
    }
}
