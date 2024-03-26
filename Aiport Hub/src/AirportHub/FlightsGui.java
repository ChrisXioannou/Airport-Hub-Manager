package AirportHub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlightsGui extends JFrame {
    private JTextArea directFlightsDetailsTextArea;
    private JTextArea indirectFlightsDetailsTextArea;
    private JButton backButton;
    private Airport departureAirport;
    private Airport destinationAirport;
    private CentralRegistry centralRegistry;

    public FlightsGui(CentralRegistry centralRegistry, Airport departure, Airport destination) {
        this.centralRegistry = centralRegistry;
        this.departureAirport = departure;
        this.destinationAirport = destination;

        directFlightsDetailsTextArea = new JTextArea(10, 50);
        indirectFlightsDetailsTextArea = new JTextArea(10, 50);

        // Layout for the direct and indirect flights details
        JPanel flightsPanel = new JPanel();
        flightsPanel.setLayout(new GridLayout(1, 2));
        flightsPanel.add(new JScrollPane(directFlightsDetailsTextArea));
        flightsPanel.add(new JScrollPane(indirectFlightsDetailsTextArea));
        
        Font boldFont = new Font("SansSerif", Font.BOLD, 12);
        directFlightsDetailsTextArea.setFont(boldFont);
        indirectFlightsDetailsTextArea.setFont(boldFont);

        // Back button to close this frame and go back
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close this frame
            }
        });

        // Display flight details
        displayFlights();

        // Main frame layout
        setLayout(new BorderLayout());
        add(flightsPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        setTitle("Flights Information");
        pack(); // Size the frame to fit content
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void displayFlights() {
        // Fetch the details as strings
        String directFlights = centralRegistry.getDirectFlightsDetailsAsString(departureAirport, destinationAirport);
        String indirectFlights = centralRegistry.getInDirectFlightsDetailsAsString(departureAirport, destinationAirport);

        // Check if no flights were found and update the strings accordingly
        if (directFlights.isEmpty()) {
            directFlights = "No direct flights found.";
        }
        if (indirectFlights.isEmpty()) {
            indirectFlights = "No indirect flights found.";
        }

        // Set the text for the text areas with titles
        directFlightsDetailsTextArea.setText("Direct Flights:\n" + directFlights);
        indirectFlightsDetailsTextArea.setText("Indirect Flights:\n" + indirectFlights);
        directFlightsDetailsTextArea.setEditable(false);
        indirectFlightsDetailsTextArea.setEditable(false);
    }
}

