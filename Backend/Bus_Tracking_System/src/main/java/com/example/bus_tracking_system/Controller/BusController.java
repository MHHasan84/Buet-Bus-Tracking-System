package com.example.bus_tracking_system.Controller;

import com.example.bus_tracking_system.Model.Bus;
import com.example.bus_tracking_system.Model.BusPerson;
import com.example.bus_tracking_system.Model.Route;
import com.example.bus_tracking_system.Model.Schedule;
import com.example.bus_tracking_system.Repository.BusPersonRepository;
import com.example.bus_tracking_system.Repository.BusRepository;
import com.example.bus_tracking_system.Repository.RouteRepository;
import com.example.bus_tracking_system.Repository.ScheduleRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BusController {
    @Autowired
    private BusRepository busRepository;
    @GetMapping("/bus")
    public List<Bus> getAllBus(){
        return busRepository.findAll();
    }
    @GetMapping("/bus/{id}")
    public Bus getBus(@PathVariable("id") int busId){
        return busRepository.findById(busId).get();
    }
    @PostMapping("/bus")
    public Bus addBus(@RequestBody Bus bus){
        return busRepository.save(bus);
    }
    @PutMapping("/bus/{id}")
    public Bus editBus(@RequestBody Bus bus,@PathVariable("id") int busId){
        bus.setBusId(busId);
        return busRepository.save(bus);
    }
    @DeleteMapping("/bus/{id}")
    void deleteBus(@PathVariable("id") int busId){
        busRepository.deleteById(busId);
    }
}
