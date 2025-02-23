package org.example.it_support_ticket_systemcontext.UI;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketDashboard extends JFrame {
    private JTable ticketTable;
    private JButton refreshButton;
    private String token;
    private String firstname;

    public TicketDashboard(String firstname, String token) {
        this.firstname = firstname;
        this.token = token;
        setTitle("Welcome " + firstname + " - Ticket Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new MigLayout("", "[grow]", "[][grow][]"));

        refreshButton = new JButton("Refresh Tickets");
        add(refreshButton, "wrap");

        ticketTable = new JTable();
        add(new JScrollPane(ticketTable), "grow, span");

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTickets();
            }
        });

        setLocationRelativeTo(null);
    }

    private void refreshTickets() {
        try {
            String ticketsJson = ApiClient.getAllTickets();
            JOptionPane.showMessageDialog(this, "Tickets:\n" + ticketsJson);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading tickets: " + e.getMessage());
        }
    }
}
