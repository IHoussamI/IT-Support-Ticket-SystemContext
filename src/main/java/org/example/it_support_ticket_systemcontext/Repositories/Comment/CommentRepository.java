package org.example.it_support_ticket_systemcontext.Repositories;

import org.example.it_support_ticket_systemcontext.Models.Comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTicket_TicketId(Long ticketId);
}
