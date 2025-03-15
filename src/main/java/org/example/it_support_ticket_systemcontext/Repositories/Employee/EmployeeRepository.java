package org.example.it_support_ticket_systemcontext.Repositories;

import org.example.it_support_ticket_systemcontext.Models.Employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(Long aLong);
    Optional<Employee> findByEmail(String email);
}
