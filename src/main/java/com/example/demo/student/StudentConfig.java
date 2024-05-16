package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student doe = new Student(
                    "Doe",
                    "Doe@gmail.com",
                    LocalDate.of(2000, Month.FEBRUARY,4),
                    23
        );
            Student alex = new Student( "Alex",
                    "Alex12@gmail.com",
                    LocalDate.of(2003, Month.NOVEMBER,6),
                    33
        );
       repository.saveAll(
        List.of(doe,alex)
);
        };
    }
}
