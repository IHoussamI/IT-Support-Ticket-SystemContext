package org.example.it_support_ticket_systemcontext.UI;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginFrame() {
        setTitle("Login - IT Support Ticket System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 220);
        setLayout(new MigLayout("", "[grow,fill]", "[]10[]10[]20[]10[]"));

        // Email input
        add(new JLabel("Email:"));
        emailField = new JTextField(20);
        add(emailField, "wrap");

        // Password input
        add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        add(passwordField, "wrap");

        // Login button
        loginButton = new JButton("Login");
        add(loginButton, "span, center, wrap");

        // Status label for messages
        statusLabel = new JLabel();
        add(statusLabel, "span, center");

        loginButton.addActionListener(new LoginAction());
        setLocationRelativeTo(null);
    }


    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                statusLabel.setText("Please enter both email and password.");
                return;
            }

            loginButton.setEnabled(false);
            statusLabel.setText("Logging in...");

            SwingUtilities.invokeLater(() -> {
                try {
                    AuthenticationResponse response = ApiClient.login(email, password);
                    if (response != null && response.getToken() != null) {
                        statusLabel.setText("Welcome " + response.getFirstname() + "!");
                        dispose();
                        new TicketDashboard(response.getFirstname(), response.getToken()).setVisible(true);
                    } else {
                        statusLabel.setText("Invalid email or password.");
                        passwordField.setText("");
                    }
                } catch (Exception ex) {
                    statusLabel.setText("Error: " + ex.getMessage());
                    passwordField.setText("");
                } finally {
                    loginButton.setEnabled(true);
                }
            });
        }
    }
}
