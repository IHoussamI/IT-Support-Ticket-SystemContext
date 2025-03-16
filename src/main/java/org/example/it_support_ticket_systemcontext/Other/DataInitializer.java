package org.example.it_support_ticket_systemcontext.Other;


import org.springframework.beans.factory.annotation.Value;
import org.example.it_support_ticket_systemcontext.Models.Employee.Employee;
import org.example.it_support_ticket_systemcontext.Repositories.Employee.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.example.it_support_ticket_systemcontext.Role.Role.IT_SUPPORT;

@Configuration
public class DataInitializer {


    @Value("${dev.it-support1.email}")
    private String itSupport1Email;

    @Value("${dev.it-support1.password}")
    private String itSupport1Password;

    @Value("${test.it-support1.email}")
    private String itSupport2Email;

    @Value("${test.it-support1.password}")
    private String itSupport2Password;

    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("Checking if database is empty...");
            if (employeeRepository.findAll().isEmpty()) {
                System.out.println("Database is empty. Creating IT support accounts...");

                String hashedPassword1 = passwordEncoder.encode(itSupport1Password);
                String hashedPassword2 = passwordEncoder.encode(itSupport2Password);

                Employee itSupport1 = new Employee();
                itSupport1.setFirstname("houssam");
                itSupport1.setLastname("mesk");
                itSupport1.setEmail(itSupport1Email);
                itSupport1.setPassword(hashedPassword1);
                itSupport1.setRole(IT_SUPPORT);

                Employee itSupport2 = new Employee();
                itSupport2.setFirstname("ayoub");
                itSupport2.setLastname("berhili");
                itSupport2.setEmail(itSupport2Email);
                itSupport2.setPassword(hashedPassword2);
                itSupport2.setRole(IT_SUPPORT);

                employeeRepository.save(itSupport1);
                employeeRepository.save(itSupport2);


                System.out.println("✅ Two IT Support accounts created successfully.");
                System.out.println("Saved Employee 1: " + itSupport1);
                System.out.println("Saved Employee 2: " + itSupport2);
            } else {
                System.out.println("✅ Database already initialized.");

            }
        };
    }
}