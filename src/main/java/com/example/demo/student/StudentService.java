package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
       Optional <Student> studentByEmail = studentRepository.
               findStudentByEmail(student.getEmail());
       if(studentByEmail.isPresent()) {
           throw new IllegalArgumentException("email token");
       }
       studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
      boolean exists = studentRepository.existsById(studentId);
      if(!exists){
          throw new IllegalArgumentException("student with id"+ studentId+" does not exist");
      }
      studentRepository.deleteById(studentId);

    }
   @Transactional
    public void updateStudent(Long studentId, String name,
                              String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("student with id"+ studentId+" does not exist"));
        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()) {
                throw new IllegalArgumentException("email token");
            }
            student.setEmail(email);
        }
        studentRepository.save(student);

    }
}
