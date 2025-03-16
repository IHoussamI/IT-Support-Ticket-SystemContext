package org.example.it_support_ticket_systemcontext.Services.Employee;

import org.example.it_support_ticket_systemcontext.Models.Employee.Employee;
import org.example.it_support_ticket_systemcontext.Repositories.Employee.EmployeeRepository;
import org.example.it_support_ticket_systemcontext.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
}
