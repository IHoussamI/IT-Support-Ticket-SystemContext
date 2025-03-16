package org.example.it_support_ticket_systemcontext.Models.Ticket;

import jakarta.persistence.*;
import lombok.Data;
import org.example.it_support_ticket_systemcontext.Models.AuditLog.AuditLog;
import org.example.it_support_ticket_systemcontext.Models.Employee.Employee;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "TICKETS")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private String title;
    @Lob
    @Column(name = "DESCRIPTION", columnDefinition = "CLOB", nullable = false)
    private String description;
    private String priority;
    private String category;
    private LocalDateTime creationDate = LocalDateTime.now();
    private String status = "New";

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    private Employee employee;

}