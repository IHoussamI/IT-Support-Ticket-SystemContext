package org.example.it_support_ticket_systemcontext.UI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TicketServiceUI {
    public void createTicket(String title, String description, String priority, String category) throws IOException {

        String jsonInputString = String.format(
                "{\"title\": \"%s\", \"description\": \"%s\", \"priority\": \"%s\", \"category\":  \"%s\"}",
                title, description, priority, category
        );
        HttpService.sendPostRequest("http://localhost:8080/api/tickets", jsonInputString);
    }

    public void loadTickets(JTable ticketTable ) throws IOException {
        String url=("http://localhost:8080/api/tickets");
        String response = HttpService.sendGetRequest(url);

        List<Map<String, Object>> tickets = HttpService.parseJsonResponse(response);

        String[] columnNames = {"Ticket ID", "Title", "Description", "Priority", "Category", "Creation Date", "Status"};
        Object[][] data = new Object[tickets.size()][columnNames.length];

        for (int i = 0; i < tickets.size(); i++) {
            Map<String, Object> ticket = tickets.get(i);
            data[i][0] = ticket.get("ticketId");
            data[i][1] = ticket.get("title");
            data[i][2] = ticket.get("description");
            data[i][3] = ticket.get("priority");
            data[i][4] = ticket.get("category");
            data[i][5] = ticket.get("creationDate");
            data[i][6] = ticket.get("status");
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ticketTable.setModel(tableModel);
    }
        public void addComment(String ticketId) {
            String commentText = JOptionPane.showInputDialog(null, "Enter your comment for Ticket ID: " + ticketId);
            if (commentText != null && !commentText.trim().isEmpty()) {
                String jsonInputString = String.format("{\"ticketId\": \"%s\", \"commentText\": \"%s\"}", ticketId, commentText);

                try {
                    String response = HttpService.sendPostRequest("http://localhost:8080/api/tickets/" + ticketId + "/comments", jsonInputString);
                    JOptionPane.showMessageDialog(null, "Comment added successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to add comment: " + ex.getMessage());
                }
            }
        }
        public void updateStatus(String ticketId) {
            String newStatus = JOptionPane.showInputDialog(null, "Enter new status for Ticket ID: " + ticketId);
            if (newStatus != null && !newStatus.trim().isEmpty()) {
                String jsonInputString = String.format("{ \"newStatus\": \"%s\"}", newStatus);

                try {
                    String url = "http://localhost:8080/api/tickets/" + ticketId + "/status";
                    String response = HttpService.sendPutRequest(url, jsonInputString);
                    JOptionPane.showMessageDialog(null, "Status updated successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to update status: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Status cannot be empty.");
            }
        }
    public void viewAuditLogs(String ticketId) {
        try {
            String response = HttpService.sendGetRequest("http://localhost:8080/api/audit-logs/by-ticket?ticketId=" + ticketId);

            List<Map<String, Object>> auditLogs = HttpService.parseJsonResponse(response);

            StringBuilder logsText = new StringBuilder();
            logsText.append("Audit Logs for Ticket ID: ").append(ticketId).append("\n\n");

            for (Map<String, Object> log : auditLogs) {
                String actionType = (String) log.get("actionType");
                logsText.append("Action: ").append(actionType).append("\n");

                if ("Comment Added".equals(actionType)) {
                    logsText.append("Comment: ").append(log.get("commentText")).append("\n")
                            .append("Change Date: ").append(log.get("changeDate")).append("\n");
                } else {
                    logsText.append("Old Status: ").append(log.get("oldStatus")).append("\n")
                            .append("New Status: ").append(log.get("newStatus")).append("\n")
                            .append("Change Date: ").append(log.get("changeDate")).append("\n");
                }

                logsText.append("----------------------------\n");
            }

            JTextArea textArea = new JTextArea(logsText.toString(), 20, 50);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(null, scrollPane, "Audit Logs for Ticket " + ticketId, JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Failed to fetch audit logs: " + ex.getMessage());
        }
    }
    }
