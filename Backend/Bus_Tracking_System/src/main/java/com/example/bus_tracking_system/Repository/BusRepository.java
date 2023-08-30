package com.example.bus_tracking_system.Repository;

import com.example.bus_tracking_system.Model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus,Integer> {
}
