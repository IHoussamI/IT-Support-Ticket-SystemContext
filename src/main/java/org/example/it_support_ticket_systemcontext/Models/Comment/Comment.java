package org.example.it_support_ticket_systemcontext.Models.Comment;

import jakarta.persistence.*;
import lombok.Data;
import org.example.it_support_ticket_systemcontext.Models.Employee.Employee;
import org.example.it_support_ticket_systemcontext.Models.Ticket.Ticket;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Lob
    @Column(name = "comment_text", columnDefinition = "CLOB", nullable = false)
    private String commentText;
    private LocalDateTime commentDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}