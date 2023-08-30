package com.example.bus_tracking_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusController {
    @Autowired
    private BusRepository busRepository;

    @GetMapping("/bus")
    public List<Bus> getBus(){
        List<Bus> busList=busRepository.findAll();
        return busList;
    }
}
