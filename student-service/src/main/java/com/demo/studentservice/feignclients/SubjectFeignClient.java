package com.demo.studentservice.feignclients;

import com.demo.studentservice.model.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "subject-service")
@RequestMapping("/subject")
public interface SubjectFeignClient {

    @PostMapping()
    Subject save(@RequestBody Subject subject);

    @GetMapping("/bystudent/{userId}")
    List<Subject> getSubjects(@PathVariable("studentId") int studentId);
}
