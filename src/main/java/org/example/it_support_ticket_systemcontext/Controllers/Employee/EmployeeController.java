package org.example.it_support_ticket_systemcontext.Controllers;

import org.example.it_support_ticket_systemcontext.Models.Employee.Employee;
import org.example.it_support_ticket_systemcontext.Repositories.Employee.EmployeeRepository;
import org.example.it_support_ticket_systemcontext.Role.Role;
import org.example.it_support_ticket_systemcontext.Services.Employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee createEmployee(@RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam Role role) {
        return employeeService.createEmployee(email, password, role);
    }
}