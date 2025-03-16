package org.example.it_support_ticket_systemcontext.Repositories.Ticket;

import org.example.it_support_ticket_systemcontext.Models.Employee.Employee;
import org.example.it_support_ticket_systemcontext.Models.Ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatus(String status);
    List<Ticket> findByEmployee(Employee employee);

    List<Ticket> findByEmployeeId(Long employeeId);

}