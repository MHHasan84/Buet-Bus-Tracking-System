package com.example.bus_tracking_system.Controller;

import com.example.bus_tracking_system.Model.PickupPoint;
import com.example.bus_tracking_system.Repository.PickupPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PickupPointController {
    @Autowired
    private PickupPointRepository pickupPointRepository;
    @GetMapping("/pickup-point/{id}")
    List<PickupPoint> getAllPickupPoint(int routeId){
        return null;
    }
    @PostMapping("/pickup-point")
    PickupPoint addPickupPoint(@RequestBody PickupPoint pickupPoint){
        return pickupPointRepository.save(pickupPoint);
    }
    @PutMapping("/pickup-point/{id}")
    PickupPoint editPickupPoint(@RequestBody PickupPoint pickupPoint,@PathVariable("id") int pickupPointId){
        pickupPoint.setPickupPointId(pickupPointId);
        return pickupPointRepository.save(pickupPoint);
    }
    @DeleteMapping("/pickup-point/{id}")
    void deletePickupPoint(@PathVariable("id") int pickupPointId){
        pickupPointRepository.deleteById(pickupPointId);
    }
}
