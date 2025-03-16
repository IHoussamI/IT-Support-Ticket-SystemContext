package org.example.it_support_ticket_systemcontext.DTOs.AuditLog;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class AuditLogDTO {
    private Long logId;
    private Long ticketId;
    private String actionType;
    private String oldStatus;
    private String newStatus;
    private String commentText;
    private LocalDateTime changeDate;

}