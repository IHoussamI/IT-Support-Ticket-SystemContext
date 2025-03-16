package org.example.it_support_ticket_systemcontext.DTOs.Ticket;

import org.example.it_support_ticket_systemcontext.Models.Ticket.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketDTO toDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setTicketId(ticket.getTicketId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setPriority(ticket.getPriority());
        dto.setCategory(ticket.getCategory());
        dto.setCreationDate(ticket.getCreationDate());
        dto.setStatus(ticket.getStatus());
        return dto;
    }
}
