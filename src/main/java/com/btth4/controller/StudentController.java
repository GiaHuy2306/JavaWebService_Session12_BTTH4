package com.btth4.controller;

import com.btth4.model.Student;
import com.btth4.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        // YÊU CẦU LOGGING: INFO cho mỗi request đến controller
        logger.info("[SLF4J INFO] Request đến endpoint: GET /api/students");
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        logger.info("[SLF4J INFO] Request đến endpoint: GET /api/students/{}", id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
}
