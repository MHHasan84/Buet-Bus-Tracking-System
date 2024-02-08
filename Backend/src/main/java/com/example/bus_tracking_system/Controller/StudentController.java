package com.example.bus_tracking_system.Controller;

import com.example.bus_tracking_system.Model.Student;
import com.example.bus_tracking_system.Model.Ticket;
import com.example.bus_tracking_system.Repository.StudentRepository;
import com.example.bus_tracking_system.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @GetMapping("/student")
    List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    @GetMapping("/student/{studentId}")
    Student getStudent(@PathVariable("studentId") String studentId){
        return studentRepository.findById(studentId).get();
    }
    @PostMapping("/student")
    Student addStudent(@RequestBody Student student){
        Ticket ticket=new Ticket();
        ticket.setTicketId(student.getTicketId());
        ticket.setNumberOfTickets(0);
        ticket.setValidity(true);
        ticketRepository.save(ticket);
        return studentRepository.save(student);
    }
    @PutMapping("/student/{studentId}")
    Student editStudent(@RequestBody Student student,@PathVariable("studentId") String studentId){
        student.setStudentId(studentId);
        return studentRepository.save(student);
    }
    @DeleteMapping("/student/{studentId}")
    void deleteStudent(@PathVariable("studentId") String studentId){
        studentRepository.deleteById(studentId);
    }
}
