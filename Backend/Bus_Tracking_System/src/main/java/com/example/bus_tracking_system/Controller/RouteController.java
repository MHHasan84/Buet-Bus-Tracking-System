package com.example.bus_tracking_system.Controller;

import com.example.bus_tracking_system.Model.Route;
import com.example.bus_tracking_system.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RouteController {
    @Autowired
    private RouteRepository routeRepository;
    @GetMapping("/route")
    List<Route> getAllRoute(){
        return routeRepository.findAll();
    }
    @GetMapping("/route/{id}")
    Route getRoute(@PathVariable("id") int routeId){
        return routeRepository.findById(routeId).get();
    }
    @PostMapping("/route")
    Route addRoute(@RequestBody Route route){
        return routeRepository.save(route);
    }
    @PutMapping("/route/{id}")
    Route editRoute(@RequestBody Route route,@PathVariable("id") int routeId){
        route.setRouteId(routeId);
        return routeRepository.save(route);
    }
    @DeleteMapping("/route/{id}")
    void deleteRoute(@PathVariable("id") int routeId){
        routeRepository.deleteById(routeId);
    }
}
