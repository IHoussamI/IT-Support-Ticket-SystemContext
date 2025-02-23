package org.example.it_support_ticket_systemcontext.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "TICKETS")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private String title;
    @Lob
    @Column(name = "description", columnDefinition = "CLOB", nullable = false)
    private String description;
    private String priority;
    private String category;
    private LocalDateTime creationDate = LocalDateTime.now();
    private String status = "New";

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}