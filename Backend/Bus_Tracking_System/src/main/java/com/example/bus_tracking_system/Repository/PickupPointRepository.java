package com.example.bus_tracking_system.Repository;

import com.example.bus_tracking_system.Model.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickupPointRepository extends JpaRepository<PickupPoint,Integer> {
}
