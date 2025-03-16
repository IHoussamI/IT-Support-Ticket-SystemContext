package org.example.it_support_ticket_systemcontext.Models.AuditLog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.it_support_ticket_systemcontext.Models.Employee.Employee;
import org.example.it_support_ticket_systemcontext.Models.Ticket.Ticket;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "AUDIT_LOG")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "TICKET_ID")
    private Ticket ticket;

    private String actionType;
    private String oldStatus;
    private String newStatus;

    @JoinColumn(name = "COMMENT_TEXT")
    private String commentText;

    private LocalDateTime changeDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

}

