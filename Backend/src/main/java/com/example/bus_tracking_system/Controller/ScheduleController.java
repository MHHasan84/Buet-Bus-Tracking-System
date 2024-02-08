package com.example.bus_tracking_system.Controller;

import com.example.bus_tracking_system.Model.Schedule;
import com.example.bus_tracking_system.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @GetMapping("/schedule/{id}")
    List<Schedule> getAllSchedule(@PathVariable("id") int busId){
        return null;
    }
    @PostMapping("/schedule")
    Schedule addSchedule(@RequestBody Schedule schedule){
        return scheduleRepository.save(schedule);
    }
    @PutMapping("/schedule/{id}")
    Schedule editSchedule(@RequestBody Schedule schedule,@PathVariable("id") int scheduleId){
        schedule.setScheduleId(scheduleId);
        return scheduleRepository.save(schedule);
    }
    @DeleteMapping("/schedule/{id}")
    void deleteSchedule(@PathVariable("id") int scheduleId){
        scheduleRepository.deleteById(scheduleId);
    }
}
