package AirportHub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroGui extends JFrame {

    public IntroGui(CentralRegistry centralRegistry) {
        super("Intro Page");

        // Main panel with BoxLayout for arranging button panels vertically
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Panel for User and Admin buttons
        JPanel userAdminPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton userButton = new JButton("User");
        JButton adminButton = new JButton("Admin");
        userAdminPanel.add(userButton);
        userAdminPanel.add(adminButton);

        // Panel for View buttons
        JPanel viewPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton viewAirportsButton = new JButton("View Existing Airports");
        JButton viewFlightsButton = new JButton("View Existing Flights");
        viewPanel.add(viewAirportsButton);
        viewPanel.add(viewFlightsButton);

        // Add button panels to the main panel
        mainPanel.add(userAdminPanel);
        mainPanel.add(viewPanel);

        // Add action listeners for buttons
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose IntroGui before opening the new window
                new SearchGui(centralRegistry).setVisible(true);
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose IntroGui before opening the new window
                new PasswordGui(centralRegistry).setVisible(true);
            }
        });

        viewAirportsButton.addActionListener(e -> displayAirports(centralRegistry));
        viewFlightsButton.addActionListener(e -> displayFlights(centralRegistry));

        // Add the main panel to the frame
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Set frame properties
        setSize(500, 200); // Adjusted the size to better accommodate the buttons
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void displayAirports(CentralRegistry centralRegistry) {
        StringBuilder airportsList = new StringBuilder();
        for (Airport airport : centralRegistry.getAllAirports()) {
            airportsList.append(airport).append("\n");
        }
        JOptionPane.showMessageDialog(this, airportsList.toString(), "Existing Airports", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayFlights(CentralRegistry centralRegistry) {
        StringBuilder flightsList = new StringBuilder();
        for (Flight flight : centralRegistry.getAllFlights()) {
            flightsList.append(flight).append("\n");
        }
        JOptionPane.showMessageDialog(this, flightsList.toString(), "Existing Flights", JOptionPane.INFORMATION_MESSAGE);
    }
}