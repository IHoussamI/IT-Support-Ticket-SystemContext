package org.example.it_support_ticket_systemcontext.Repositories;

import org.example.it_support_ticket_systemcontext.Models.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByTicket_TicketId(Long ticketId);
}