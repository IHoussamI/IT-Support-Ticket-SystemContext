package org.example.it_support_ticket_systemcontext.Repositories;

import org.example.it_support_ticket_systemcontext.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatus(String status);
    List<Ticket> findByTitleContainingIgnoreCase(String title);
}