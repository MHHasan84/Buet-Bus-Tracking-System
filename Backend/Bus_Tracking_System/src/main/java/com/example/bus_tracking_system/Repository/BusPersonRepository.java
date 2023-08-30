package com.example.bus_tracking_system.Repository;

import com.example.bus_tracking_system.Model.BusPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusPersonRepository extends JpaRepository<BusPerson,Integer> {
}
