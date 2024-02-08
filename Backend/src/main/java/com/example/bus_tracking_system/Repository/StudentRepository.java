package com.example.bus_tracking_system.Repository;

import com.example.bus_tracking_system.Model.BusPerson;
import com.example.bus_tracking_system.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String> {
    @Query("select s from Student s where s.studentId=:id and s.password=:password")
    List<Student> getStudentByStudentIdAndPassword(@Param("id") String studentId,@Param("password") String password);
}
