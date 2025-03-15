package org.example.it_support_ticket_systemcontext.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "AUDIT_LOG")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    private String oldStatus;
    private String newStatus;
    private LocalDateTime changeDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}