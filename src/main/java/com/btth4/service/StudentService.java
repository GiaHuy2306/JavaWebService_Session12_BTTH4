package com.btth4.service;

import com.btth4.model.Student;
import com.btth4.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public StudentService() {
        // Khởi tạo sẵn thông tin của bạn vào hệ thống cho ý nghĩa nhé!
        students.add(new Student(idCounter.getAndIncrement(), "B24DTCN228", "Nguyễn Công Gia Huy", "CNTT", 3.6));
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Không tìm thấy sinh viên có ID: " + id));
    }
}
