package org.example.it_support_ticket_systemcontext.Services;

import org.example.it_support_ticket_systemcontext.Models.Ticket;
import org.example.it_support_ticket_systemcontext.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketsByStatus(String status) {
        return ticketRepository.findByStatus(status);
    }

    public List<Ticket> searchTickets(String keyword) {
        return ticketRepository.findByTitleContainingIgnoreCase(keyword);
    }


    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    public Ticket updateTicketStatus(Long ticketId, String newStatus) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));
        ticket.setStatus(newStatus);
        return ticketRepository.save(ticket);
    }
}