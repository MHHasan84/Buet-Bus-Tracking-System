package com.example.bus_tracking_system.Repository;

import com.example.bus_tracking_system.Model.BusPerson;
import com.example.bus_tracking_system.Model.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusPersonRepository extends JpaRepository<BusPerson,Integer> {
    @Query("select p from BusPerson p where p.busPersonType=:type")
    List<BusPerson> getPersonByType(@Param("type") String personType);
}
