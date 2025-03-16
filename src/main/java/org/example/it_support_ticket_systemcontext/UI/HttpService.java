package org.example.it_support_ticket_systemcontext.UI;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;


public class HttpService {
    @Setter
    private static String jwtToken;

    public static String sendPostRequest(String urlString, String jsonInputString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        if (jwtToken != null) {
            connection.setRequestProperty("Authorization", "Bearer " + jwtToken);
        }
        connection.setDoOutput(true);

        byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
        connection.getOutputStream().write(input, 0, input.length);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } else {
            throw new IOException("HTTP error code: " + responseCode);
        }
    }

    public static String sendGetRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        if (jwtToken != null) {
            connection.setRequestProperty("Authorization", "Bearer " + jwtToken);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } else {
            throw new IOException("HTTP error code: " + responseCode);
        }
    }

    public static String sendPutRequest(String urlString, String jsonInputString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        if (jwtToken != null) {
            connection.setRequestProperty("Authorization", "Bearer " + jwtToken);
        }
        connection.setDoOutput(true);

        byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
        connection.getOutputStream().write(input, 0, input.length);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } else {
            throw new IOException("HTTP error code: " + responseCode);
        }
    }
    public static List<Map<String, Object>> parseJsonResponse(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});
    }
}