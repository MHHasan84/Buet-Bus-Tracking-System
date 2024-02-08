package com.example.bus_tracking_system.Controller;

import com.example.bus_tracking_system.Model.BusPerson;
import com.example.bus_tracking_system.Repository.BusPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusPersonController {
    @Autowired
    private BusPersonRepository busPersonRepository;

    @GetMapping("person")
    List<BusPerson> getAllPerson(){
        return busPersonRepository.findAll();
    }
    @GetMapping("/driver")
    List<BusPerson> getAllDriver(){
        return busPersonRepository.getPersonByType("driver");
    }
    @GetMapping("/helper")
    List<BusPerson> getAllHelper(){
        return busPersonRepository.getPersonByType("helper");
    }
    @GetMapping("/person/{id}")
    BusPerson getPerson(@PathVariable("id") int personId){
        return busPersonRepository.findById(personId).get();
    }
    @PostMapping("/person")
    BusPerson addPerson(@RequestBody BusPerson busPerson){
        return busPersonRepository.save(busPerson);
    }
    @PutMapping("/person/{id}")
    BusPerson editBusPerson(@RequestBody BusPerson busPerson,@PathVariable("id") int personId){
        busPerson.setBusPersonId(personId);
        return busPersonRepository.save(busPerson);
    }
    @DeleteMapping("/person/{id}")
    void deletePerson(@PathVariable("id") int personId){
        busPersonRepository.deleteById(personId);
    }
}
