package com.example.bus_tracking_system.Controller;

import com.example.bus_tracking_system.Model.Ticket;
import com.example.bus_tracking_system.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/ticket/{ticketId}")
    Ticket getTicket(@PathVariable("ticketId") String ticketId){
        return ticketRepository.findById(ticketId).get();
    }
    @PostMapping("/ticket")
    Ticket addTicket(@RequestBody Ticket ticket){
        return ticketRepository.save(ticket);
    }
    @PutMapping("/ticket/{ticketId}")
    Ticket editTicket(@RequestBody Ticket ticket,@PathVariable("ticketId") String ticketId){
        ticket.setTicketId(ticketId);
        return ticketRepository.save(ticket);
    }
    @DeleteMapping("/ticket/{ticketId}")
    void deleteTicket(@PathVariable("ticketId") String ticketId){
        ticketRepository.deleteById(ticketId);
    }
}
