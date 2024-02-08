package com.example.bus_tracking_system.Repository;

import com.example.bus_tracking_system.Model.Bus;
import com.example.bus_tracking_system.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus,Integer> {
    @Query("select b from Bus b where b.busNumber=:id and b.password=:password")
    List<Bus> getBusByBusNumberAndPassword(@Param("id") String busNumber, @Param("password") String password);
}
