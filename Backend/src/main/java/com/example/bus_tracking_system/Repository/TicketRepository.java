package com.example.bus_tracking_system.Repository;

import com.example.bus_tracking_system.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,String> {
}
