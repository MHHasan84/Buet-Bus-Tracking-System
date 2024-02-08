package com.example.bus_tracking_system.Controller;

import com.example.bus_tracking_system.Model.Bus;
import com.example.bus_tracking_system.Model.SignInBody;
import com.example.bus_tracking_system.Model.Student;
import com.example.bus_tracking_system.Repository.BusRepository;
import com.example.bus_tracking_system.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SignInController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BusRepository busRepository;

    @PostMapping("signin/student")
    String signInStudent(@RequestBody SignInBody signInBody){
        String id=signInBody.getId();
        String password=signInBody.getPassword();

        List<Student> list=studentRepository.getStudentByStudentIdAndPassword(id,password);
        if(list.size()>0){
            return "true";
        }

        return "false";
    }
    @PostMapping("signin/admin")
    String signInAdmin(@RequestBody SignInBody signInBody){
        String id=signInBody.getId();
        String password=signInBody.getPassword();

        if(id.equals("admin") && password.equals("admin")){
            return "true";
        }

        return "false";
    }
    @PostMapping("signin/bus")
    String signInBus(@RequestBody SignInBody signInBody){
        String id=signInBody.getId();
        String password=signInBody.getPassword();

        List<Bus> list=busRepository.getBusByBusNumberAndPassword(id,password);
        if(list.size()>0){
            return "true";
        }

        return "false";
    }
}
