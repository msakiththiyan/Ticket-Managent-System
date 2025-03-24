package com.oop.coursework.ticketSystem.controller;

import com.oop.coursework.ticketSystem.model.Configuration;
import com.oop.coursework.ticketSystem.model.Ticket;
import com.oop.coursework.ticketSystem.service.TicketVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/ticket-vendor")
public class TicketVendorController {
    private final TicketVendorService ticketVendorService;

    @Autowired
    public TicketVendorController(TicketVendorService ticketVendorService) {
        this.ticketVendorService = ticketVendorService;
    }

    @GetMapping("/configuration")
    public ResponseEntity<Configuration> getCurrentConfiguration() {
        return ResponseEntity.ok(ticketVendorService.getCurrentConfiguration());
    }

    @PostMapping("/configuration")
    public ResponseEntity<Configuration> configureSystem(@RequestBody Configuration configuration) {
        ticketVendorService.ConfigureSystem(configuration);
        return ResponseEntity.ok(configuration);
    }

    @PostMapping("/run")
    public ResponseEntity<String> runTicketSystem() {
        try{
            ticketVendorService.runTicketSystem();
            return ResponseEntity.ok("Ticket system Executed.");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getSoldTickets(){
        return ResponseEntity.ok(ticketVendorService.getSoldTickets());
    }
}
