package com.albick.demospringboot.service;

import com.albick.demospringboot.entity.Student;
import com.albick.demospringboot.exception.RecordNotFoundException;
import com.albick.demospringboot.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(long id) throws RecordNotFoundException {
        var studentResult = studentRepository.findById(id);
        if (studentResult.isEmpty()) {
            throw new RecordNotFoundException(Student.class.getSimpleName());
        }
        return studentResult.get();
    }

    public Student createStudent(Student student) {
        var studentCreated = studentRepository.save(student);
        return studentCreated;
    }
}
