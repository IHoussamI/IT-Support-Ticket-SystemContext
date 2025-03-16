package org.example.it_support_ticket_systemcontext.DTOs.Ticket;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TicketDTO {
    private Long ticketId;
    private String title;
    private String description;
    private String priority;
    private String category;
    private LocalDateTime creationDate;
    private String status;
}