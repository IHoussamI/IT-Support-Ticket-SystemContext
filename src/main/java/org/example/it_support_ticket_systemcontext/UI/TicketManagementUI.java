package org.example.it_support_ticket_systemcontext.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicketManagementUI extends JFrame {
    private JTable ticketTable;
    private TicketServiceUI ticketService;
    private String userRole;
    private String userFirstname;

    public TicketManagementUI(String userRole, String userFirstname) {
        this.userRole = userRole;
        this.userFirstname = userFirstname;
        this.ticketService = new TicketServiceUI();
        initUI();
    }

    private void initUI() {
        setTitle("Ticket Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        ticketTable = new JTable();


        JScrollPane scrollPane = new JScrollPane(ticketTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton createTicketButton = new JButton("Create Ticket");
        add(createTicketButton, BorderLayout.SOUTH);

        JButton viewAuditLogsButton = new JButton("View Audit Logs");
        viewAuditLogsButton.setVisible("IT_SUPPORT".equals(userRole));
        add(viewAuditLogsButton, BorderLayout.WEST);

        JPanel topPanel = new JPanel(new BorderLayout());

        JButton refreshButton = new JButton("Refresh Tickets");
        topPanel.add(refreshButton, BorderLayout.EAST);

        JLabel welcomeLabel = new JLabel("Welcome, " + userFirstname);
        topPanel.add(welcomeLabel, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);


        refreshButton.addActionListener(e -> refreshTickets());
        createTicketButton.addActionListener(e -> createTicket());
        viewAuditLogsButton.addActionListener(e -> viewAuditLogs());
        if("IT_SUPPORT".equals(userRole)){
            addRightClickMenu(ticketTable);
            setVisible(true);

        }
        setVisible(true);
    }

    private void refreshTickets() {
        try {
            ticketService.loadTickets(ticketTable);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load tickets: " + ex.getMessage());
        }
    }

    private void createTicket() {
        String title = JOptionPane.showInputDialog(this, "Enter ticket title:");
        String description = JOptionPane.showInputDialog(this, "Enter ticket description:");
        String priority = JOptionPane.showInputDialog(this, "Enter ticket priority:");
        String category = JOptionPane.showInputDialog(this, "Enter ticket category:");

        try {
            ticketService.createTicket(title, description, priority, category);
            JOptionPane.showMessageDialog(this, "Ticket created successfully!");
            refreshTickets();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to create ticket: " + ex.getMessage());
        }
    }

    private void viewAuditLogs() {
        int selectedRow = ticketTable.getSelectedRow();
        if (selectedRow != -1) {
            String ticketId = ticketTable.getValueAt(selectedRow, 0).toString();
            ticketService.viewAuditLogs(ticketId);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a ticket to view its audit logs.");
        }
    }

    private void addRightClickMenu(JTable ticketTable) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem addCommentItem = new JMenuItem("Add Comment");
        JMenuItem updateStatusItem = new JMenuItem("Update Status");

        addCommentItem.addActionListener(e -> {
            int selectedRow = ticketTable.getSelectedRow();
            if (selectedRow != -1) {
                String ticketId = ticketTable.getValueAt(selectedRow, 0).toString();
                ticketService.addComment(ticketId);
            }
        });

        updateStatusItem.addActionListener(e -> {
            int selectedRow = ticketTable.getSelectedRow();
            if (selectedRow != -1) {
                String ticketId = ticketTable.getValueAt(selectedRow, 0).toString();
                ticketService.updateStatus(ticketId);
            }
        });

        popupMenu.add(addCommentItem);
        popupMenu.add(updateStatusItem);

        ticketTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = ticketTable.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        ticketTable.setRowSelectionInterval(row, row);
                        popupMenu.show(ticketTable, e.getX(), e.getY());
                    }
                }
            }
        });
    }
}