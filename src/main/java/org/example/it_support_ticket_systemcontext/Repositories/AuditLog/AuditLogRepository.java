package org.example.it_support_ticket_systemcontext.Repositories.AuditLog;

import org.example.it_support_ticket_systemcontext.Models.AuditLog.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    @Query("SELECT a FROM AuditLog a WHERE a.ticket.ticketId = :ticketId")
    List<AuditLog> findByTicketId(Long ticketId);

}
