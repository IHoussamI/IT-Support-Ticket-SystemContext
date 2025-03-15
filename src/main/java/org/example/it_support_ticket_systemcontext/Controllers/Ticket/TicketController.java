package org.example.it_support_ticket_systemcontext.Controllers;

import org.example.it_support_ticket_systemcontext.Models.Ticket.Ticket;
import org.example.it_support_ticket_systemcontext.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {

        return ticketService.createTicket(ticket);
    }

    @GetMapping
    public List<Ticket> getTickets(@RequestParam(required = false) String status,
                                   @RequestParam(required = false) String search) {
        if (status != null) {
            return ticketService.getTicketsByStatus(status);
        } else if (search != null) {
            return ticketService.searchTickets(search);
        }
        return ticketService.getAllTickets();

    }


    @PutMapping("/{ticketId}/status")
    public Ticket updateTicketStatus(@PathVariable("ticketId") Long ticketId,
                                     @RequestBody Map<String, String> statusMap) {
        String status = statusMap.get("status");
        return ticketService.updateTicketStatus(ticketId, status);
    }

}