package org.example.it_support_ticket_systemcontext.UI;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {

    private static String jwtToken;

    public static AuthenticationResponse login(String email, String password) throws Exception {
        URL url = new URL("http://localhost:8080/api/auth/authenticate");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonInput = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            JSONObject jsonObject = new JSONObject(response.toString());
            String token = jsonObject.getString("token");
            String firstname = jsonObject.getString("firstname");
            jwtToken = token;
            return new AuthenticationResponse(token, firstname);
        } else {
            throw new IOException("Login failed with response code: " + conn.getResponseCode());
        }
    }

    public static String getAllTickets() throws Exception {
        if (jwtToken == null) {
            throw new IllegalStateException("JWT token is required. Please log in first.");
        }

        URL url = new URL("http://localhost:8080/api/tickets");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + jwtToken);
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        } else {
            throw new IOException("Failed to fetch tickets with response code: " + conn.getResponseCode());
        }
    }
}

class AuthenticationResponse {
    private String token;
    private String firstname;

    public AuthenticationResponse(String token, String firstname) {
        this.token = token;
        this.firstname = firstname;
    }

    public String getToken() {
        return token;
    }

    public String getFirstname() {
        return firstname;
    }
}
