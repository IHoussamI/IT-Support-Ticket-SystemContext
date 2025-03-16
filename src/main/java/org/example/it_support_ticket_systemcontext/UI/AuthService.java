package org.example.it_support_ticket_systemcontext.UI;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.example.it_support_ticket_systemcontext.Authentication.Auth.AuthenticationResponse;

import java.io.IOException;

@Getter
public class AuthService {
    private String jwtToken;

    public boolean register(String email, String password, String firstname, String lastname) {
        String jsonInputString = String.format(
                "{\"email\": \"%s\", \"password\": \"%s\", \"firstname\": \"%s\", \"lastname\": \"%s\"}",
                email, password, firstname, lastname
        );

        try {
            String response = HttpService.sendPostRequest("http://localhost:8080/api/auth/register", jsonInputString);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public AuthenticationResponse authenticate(String email, String password) {
        String jsonInputString = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        try {
            String response = HttpService.sendPostRequest("http://localhost:8080/api/auth/authenticate", jsonInputString);
            ObjectMapper objectMapper = new ObjectMapper();

            AuthenticationResponse authResponse = objectMapper.readValue(response, AuthenticationResponse.class);

            jwtToken = authResponse.getToken();

            return authResponse;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}