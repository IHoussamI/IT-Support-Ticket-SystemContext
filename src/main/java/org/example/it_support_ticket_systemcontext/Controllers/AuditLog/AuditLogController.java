package org.example.it_support_ticket_systemcontext.Controllers.AuditLog;

import org.example.it_support_ticket_systemcontext.Models.AuditLog.AuditLog;
import org.example.it_support_ticket_systemcontext.Repositories.AuditLog.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/audit-logs")
@PreAuthorize("hasRole('IT_SUPPORT')")
public class AuditLogController {
    @Autowired
    private AuditLogRepository auditLogRepository;

    @GetMapping
    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }
    @GetMapping("/by-ticket")
    public List<AuditLog> getAuditLogsByTicketId(@RequestParam(required = false) Long ticketId) {
        if (ticketId != null) {
            return auditLogRepository.findByTicketId(ticketId);

        } else {
            throw new RuntimeException("Ticket not found");
        }
    }
}
