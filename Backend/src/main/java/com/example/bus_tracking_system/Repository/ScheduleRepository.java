package com.example.bus_tracking_system.Repository;

import com.example.bus_tracking_system.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
}
