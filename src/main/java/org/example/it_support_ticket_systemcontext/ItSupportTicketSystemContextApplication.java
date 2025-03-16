package org.example.it_support_ticket_systemcontext;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Ticket Management System API",
                version = "1.0",
                description = "API for managing IT support tickets"
        )
)

public class ItSupportTicketSystemContextApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItSupportTicketSystemContextApplication.class, args);

    }

}