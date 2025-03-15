package org.example.it_support_ticket_systemcontext.Services;

import org.example.it_support_ticket_systemcontext.Models.Employee;
import org.example.it_support_ticket_systemcontext.Repositories.EmployeeRepository;
import org.example.it_support_ticket_systemcontext.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(String email, String password, Role role) {
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setRole(role);
        return employeeRepository.save(employee);
    }
    public Employee createEmployee(String email, String password, String role) {
        try {
            Role validatedRole = Role.valueOf(role.toUpperCase());
            Employee employee = new Employee();
            employee.setEmail(email);
            employee.setPassword(password);
            employee.setRole(validatedRole);
            return employeeRepository.save(employee);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
