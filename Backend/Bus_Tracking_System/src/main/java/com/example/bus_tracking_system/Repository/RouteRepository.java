package com.example.bus_tracking_system.Repository;

import com.example.bus_tracking_system.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,Integer> {
}
