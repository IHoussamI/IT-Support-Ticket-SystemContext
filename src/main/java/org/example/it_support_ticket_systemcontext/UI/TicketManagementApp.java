package org.example.it_support_ticket_systemcontext.UI;

import org.example.it_support_ticket_systemcontext.Authentication.Auth.AuthenticationResponse;
import org.example.it_support_ticket_systemcontext.Authentication.Config.JwtService;

import javax.swing.*;
import java.awt.*;

public class TicketManagementApp extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField firstnameField;
    private JTextField lastnameField;
    private JButton actionButton;
    private JLabel messageLabel;
    private JRadioButton registerRadio;
    private JRadioButton authenticateRadio;

    private AuthService authService;
    private JwtService jwtService;

    public TicketManagementApp() {
        this.jwtService = new JwtService();

        setTitle("Ticket Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        authService = new AuthService();
        initUI();
    }

    private void initUI() {
        emailField = new JTextField();
        passwordField = new JPasswordField();
        firstnameField = new JTextField();
        lastnameField = new JTextField();
        actionButton = new JButton("Register");
        messageLabel = new JLabel();

        registerRadio = new JRadioButton("Register");
        authenticateRadio = new JRadioButton("Authenticate");
        ButtonGroup group = new ButtonGroup();
        group.add(registerRadio);
        group.add(authenticateRadio);
        registerRadio.setSelected(true);

        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("First Name:"));
        add(firstnameField);
        add(new JLabel("Last Name:"));
        add(lastnameField);
        add(registerRadio);
        add(authenticateRadio);
        add(actionButton);
        add(messageLabel);

        registerRadio.addActionListener(e -> {
            toggleRegistrationFields(true);
            actionButton.setText("Register");
        });

        authenticateRadio.addActionListener(e -> {
            toggleRegistrationFields(false);
            actionButton.setText("Authenticate");
        });

        actionButton.addActionListener(e -> {
            if (registerRadio.isSelected()) {
                register();
            } else {
                authenticate();
            }
        });

        toggleRegistrationFields(true);
    }

    private void toggleRegistrationFields(boolean show) {
        firstnameField.setVisible(show);
        lastnameField.setVisible(show);
        getContentPane().getComponent(4).setVisible(show);
        getContentPane().getComponent(6).setVisible(show);
        revalidate();
        repaint();
    }

    private void register() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();

        boolean success = authService.register(email, password, firstname, lastname);
        if (success) {
            messageLabel.setText("Registration successful!");
        } else {
            messageLabel.setText("Registration failed.");
        }
    }

    private void authenticate() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        AuthenticationResponse response = authService.authenticate(email, password);
        if (response != null) {
            String userRole =response.getRole();
            String jwtToken = response.getToken();
            String userFirstname = response.getFirstname();

            HttpService.setJwtToken(jwtToken);
            messageLabel.setText("Authentication successful!");
            dispose();
            new TicketManagementUI(userRole, userFirstname);
        } else {
            messageLabel.setText("Authentication failed.");
        }
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicketManagementApp app = new TicketManagementApp();
            app.setVisible(true);
        });
    }
}