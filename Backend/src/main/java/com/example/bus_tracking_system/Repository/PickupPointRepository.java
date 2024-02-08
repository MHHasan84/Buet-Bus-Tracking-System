package com.example.bus_tracking_system.Repository;

import com.example.bus_tracking_system.Model.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PickupPointRepository extends JpaRepository<PickupPoint,Integer> {
    @Query("select p from PickupPoint p where p.routeId=:id")
    List<PickupPoint> getPickupPointByRoute(@Param("id") int routeId);
}
