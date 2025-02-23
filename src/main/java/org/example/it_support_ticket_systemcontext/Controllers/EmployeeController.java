package org.example.it_support_ticket_systemcontext.Controllers;

import org.example.it_support_ticket_systemcontext.Models.Employee;
import org.example.it_support_ticket_systemcontext.Role.Role;
import org.example.it_support_ticket_systemcontext.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam Role role) {
        return employeeService.createEmployee(email, password, role);
    }
}