package org.example.it_support_ticket_systemcontext.Controllers.Ticket;

import org.example.it_support_ticket_systemcontext.Authentication.Config.JwtService;
import org.example.it_support_ticket_systemcontext.DTOs.AuditLog.AuditLogDTO;
import org.example.it_support_ticket_systemcontext.DTOs.Ticket.TicketDTO;
import org.example.it_support_ticket_systemcontext.DTOs.Ticket.TicketMapper;
import org.example.it_support_ticket_systemcontext.Models.AuditLog.AuditLog;
import org.example.it_support_ticket_systemcontext.Models.Comment.Comment;
import org.example.it_support_ticket_systemcontext.Models.Employee.Employee;
import org.example.it_support_ticket_systemcontext.Models.Ticket.Ticket;
import org.example.it_support_ticket_systemcontext.Repositories.Employee.EmployeeRepository;
import org.example.it_support_ticket_systemcontext.Role.Role;
import org.example.it_support_ticket_systemcontext.Services.Tickets.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TicketMapper ticketMapper;


    @PostMapping
    public TicketDTO createTicket(@RequestBody Ticket ticket, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");

        String email = jwtService.extractUsername(jwtToken);

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        ticket.setEmployee(employee);
        Ticket createdTicket = ticketService.createTicket(ticket);

        return ticketMapper.toDTO(createdTicket);


    }
    @GetMapping
    public List<TicketDTO> getTickets( @RequestHeader("Authorization") String token,
                                       @RequestParam (required = false) Long employeeId) {

        String jwtToken = token.replace("Bearer ", "");
        String email = jwtService.extractUsername(jwtToken);
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        /*System.out.println("Employee ID: " + employee.getId());
        System.out.println("Employee Role: " + employee.getRole());*/
        List<Ticket> tickets;
        if (employeeId != null) {
            tickets = ticketService.getTicketsByEmployeeId(employeeId);
        } else if (employee.getRole().equals(Role.IT_SUPPORT)) {
            tickets = ticketService.getAllTickets(employee);
        } else {
            tickets = ticketService.getTicketsByEmployeeId(employee.getId());
        }
        return tickets.stream()
                .map(ticketMapper::toDTO)
                .collect(Collectors.toList());


    }
    @PutMapping("/{ticketId}/status")
    public ResponseEntity<Ticket> updateTicketStatus(@PathVariable("ticketId") Long ticketId,
                                                    @RequestBody Map<String, String> request) {
        String newStatus = request.get("newStatus");
        if (newStatus == null) {
            throw new IllegalArgumentException("newStatus cannot be null");
        }
        Ticket updatedTicket = ticketService.updateTicketStatus(ticketId, newStatus);
        return ResponseEntity.ok(updatedTicket);
    }
    @PostMapping("/{ticketId}/comments")
    public Comment addComment(@PathVariable("ticketId") Long ticketId,
                              @RequestBody Map<String,String> requestBody){
        String commentText = requestBody.get("commentText");
        return ticketService.addComment(ticketId, commentText);
    }

    @GetMapping("/{ticketId}/comments")
    public List<Map<String, String>> getComments(@PathVariable("ticketId") Long ticketId) {
        return ticketService.getCommentsByTicket(ticketId)
                .stream()
                .map(comment -> Map.of(
                        "commentText", comment.getCommentText(),
                        "commentDate", comment.getCommentDate().toString()
                ))
                .toList();
    }

    @GetMapping("/{ticketId}")
    public Ticket getTicketById(@PathVariable("ticketId") Long ticketId) {
        return ticketService.getTicketById(ticketId);
    }
    @GetMapping("/status")
    public List<Ticket> getTicketStatuses(@RequestParam String status) {
        return ticketService.getTicketStatuses(status);
    }

}