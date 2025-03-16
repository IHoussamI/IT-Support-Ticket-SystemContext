package org.example.it_support_ticket_systemcontext.Services.Tickets;


import org.example.it_support_ticket_systemcontext.Models.AuditLog.AuditLog;
import org.example.it_support_ticket_systemcontext.Models.Comment.Comment;
import org.example.it_support_ticket_systemcontext.Models.Employee.Employee;
import org.example.it_support_ticket_systemcontext.Models.Ticket.Ticket;
import org.example.it_support_ticket_systemcontext.Repositories.AuditLog.AuditLogRepository;
import org.example.it_support_ticket_systemcontext.Repositories.Comment.CommentRepository;
import org.example.it_support_ticket_systemcontext.Repositories.Ticket.TicketRepository;
import org.example.it_support_ticket_systemcontext.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;


    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets(Employee employee) {
        if(employee.getRole().equals(Role.IT_SUPPORT)) {
            return ticketRepository.findAll();
        }
        else {
            List<Ticket> tickets = ticketRepository.findByEmployee(employee);
            System.out.println("Tickets for employee " + employee.getEmail() + ": " + tickets);

            return tickets;
        }
    }
    public Ticket updateTicketStatus(Long ticketId, String newStatus) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));
        String oldStatus = ticket.getStatus();
        ticket.setStatus(newStatus);
        Ticket updatedTicket  = ticketRepository.save(ticket);

        AuditLog auditLog = AuditLog.builder()
                .ticket(ticket)
                .actionType("Status Updated")
                .oldStatus(oldStatus)
                .newStatus(newStatus)
                .changeDate(LocalDateTime.now())
                .employee(ticket.getEmployee())
                .build();
        auditLogRepository.save(auditLog);

        return updatedTicket;
    }
    public Comment addComment(Long ticketId, String commentText) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));
        if (commentText == null) {
            throw new IllegalArgumentException("Comment text cannot be null");
        }else {


            Comment comment = new Comment();
            comment.setTicket(ticket);
            comment.setCommentText(commentText);
            Comment savedComment = commentRepository.save(comment);
            System.out.println("Comment Text in savedComment: " + commentText);


            AuditLog auditLog = AuditLog.builder()
                    .ticket(ticket)
                    .actionType("Comment Added")
                    .commentText(commentText)
                    .newStatus(ticket.getStatus())
                    .changeDate(LocalDateTime.now())
                    .build();

        System.out.println("Comment Text in AuditLog: " + commentText);
        auditLogRepository.save(auditLog);
        System.out.println("Comment Text in AuditLog: " + auditLog.getCommentText());
        return savedComment;
        }
    }
    public List<Comment> getCommentsByTicket(Long ticketId) {
        return commentRepository.findByTicket_TicketId(ticketId);
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));
    }

    public List<Ticket> getTicketStatuses(String status) {
        return ticketRepository.findByStatus(status);
    }


    public List<Ticket> getTicketsByEmployeeId(Long employeeId) {
        return ticketRepository.findByEmployeeId(employeeId);
    }
}

