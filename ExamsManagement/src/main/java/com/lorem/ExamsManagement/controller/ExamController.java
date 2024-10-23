package com.lorem.ExamsManagement.controller;


import com.lorem.ExamsManagement.model.Exam;
import com.lorem.ExamsManagement.model.ExamType;
import com.lorem.ExamsManagement.model.Semestre;
import com.lorem.ExamsManagement.model.Session;

import com.lorem.ExamsManagement.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;


    @PostMapping
    public ResponseEntity<Exam> saveExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(examService.saveExam(exam));
    }


}
