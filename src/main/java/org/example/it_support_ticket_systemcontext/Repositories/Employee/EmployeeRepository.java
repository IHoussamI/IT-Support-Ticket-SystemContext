package org.example.it_support_ticket_systemcontext.Repositories.Employee;

import org.example.it_support_ticket_systemcontext.Models.Employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(Long Long);
     Optional<Employee> findByEmail(String email);


}
