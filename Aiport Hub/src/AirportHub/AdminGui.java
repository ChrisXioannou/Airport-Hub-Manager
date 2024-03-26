package AirportHub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGui extends JFrame {

    private JTextField airportName, airportCode, airportCity, airportCountry;
    private JTextField flightDepartureCity, flightArrivalCity, flightDuration, flightAirline;
    private JButton addAirportButton, addFlightButton, backButton;
    private CentralRegistry centralRegistry;

    public AdminGui(CentralRegistry centralRegistry) {
        super("Admin Dashboard");
        this.centralRegistry = centralRegistry;

        setLayout(new GridLayout(0, 1)); // Single column layout

        // Airport fields panel
        JPanel airportPanel = new JPanel(new GridLayout(0, 2));
        airportPanel.setBorder(BorderFactory.createTitledBorder("Add Airport"));
        airportName = new JTextField();
        airportCode = new JTextField();
        airportCity = new JTextField();
        airportCountry = new JTextField();
        addField(airportPanel, "Name: ", airportName);
        addField(airportPanel, "Code: ", airportCode);
        addField(airportPanel, "City: ", airportCity);
        addField(airportPanel, "Country: ", airportCountry);
        addAirportButton = new JButton("Add Airport");
        addAirportButton.addActionListener(e -> addAirport());
        airportPanel.add(addAirportButton);

        // Flight fields panel
        JPanel flightPanel = new JPanel(new GridLayout(0, 2));
        flightPanel.setBorder(BorderFactory.createTitledBorder("Add Flight"));
        flightDepartureCity = new JTextField();
        flightArrivalCity = new JTextField();
        flightDuration = new JTextField();
        flightAirline = new JTextField();
        addField(flightPanel, "Departure City: ", flightDepartureCity);
        addField(flightPanel, "Arrival City: ", flightArrivalCity);
        addField(flightPanel, "Duration (min): ", flightDuration);
        addField(flightPanel, "Airline: ", flightAirline);
        addFlightButton = new JButton("Add Flight");
        addFlightButton.addActionListener(e -> addFlight());
        flightPanel.add(addFlightButton);

        add(airportPanel);
        add(flightPanel);
        
        backButton = new JButton("Back to Home Screen");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close this window
                new IntroGui(centralRegistry).setVisible(true); // Show the IntroGui window
            }
        });

        
        
        // Button panel with FlowLayout for the back button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);

        // Wrapper panel with GridBagLayout
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(buttonPanel); // The buttonPanel is added to the center of the wrapperPanel

        // Adding the wrapperPanel to the SOUTH will prevent it from stretching across the whole space
        add(wrapperPanel, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addField(JPanel panel, String label, JTextField field) {
        panel.add(new JLabel(label));
        panel.add(field);
    }

    private void addAirport() {
        String name = airportName.getText();
        String code = airportCode.getText();
        String city = airportCity.getText();
        String country = airportCountry.getText();

        // Check for empty fields
        if (name.isEmpty() || code.isEmpty() || city.isEmpty() || country.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Airport airport = new Airport(name, code, city, country);
        centralRegistry.addAirport(airport);
        JOptionPane.showMessageDialog(this, "Airport added successfully!");
    }

    private void addFlight() {
        String departureCity = flightDepartureCity.getText();
        String arrivalCity = flightArrivalCity.getText();
        String durationText = flightDuration.getText();
        String airline = flightAirline.getText();

        // StringBuilder to accumulate error messages
        StringBuilder errorMessage = new StringBuilder();

        // Check for empty fields
        if (departureCity.isEmpty() || arrivalCity.isEmpty() || durationText.isEmpty() || airline.isEmpty()) {
            errorMessage.append("All fields must be filled out.\n");
        }

        // Attempt to parse duration and catch number format exception
        try {
            Integer.parseInt(durationText);
        } catch (NumberFormatException e) {
            if (errorMessage.length() > 0) errorMessage.append("\n");
            errorMessage.append("Duration must be a number.\n");
        }

        // Check existence of departure and arrival airports
        Airport departAirport = centralRegistry.getAirport(departureCity);
        Airport arriveAirport = centralRegistry.getAirport(arrivalCity);

        if (departAirport == null && arriveAirport == null) {
            if (errorMessage.length() > 0) errorMessage.append("\n");
            errorMessage.append("Both departure and arrival cities do not exist.");
        } else if (departAirport == null) {
            if (errorMessage.length() > 0) errorMessage.append("\n");
            errorMessage.append("Departure city does not exist.");
        } else if (arriveAirport == null) {
            if (errorMessage.length() > 0) errorMessage.append("\n");
            errorMessage.append("Arrival city does not exist.");
        }

        // If there were any errors, display them and return early
        if (errorMessage.length() > 0) {
            JOptionPane.showMessageDialog(this, errorMessage.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If all checks pass, proceed to add the flight
        int duration = Integer.parseInt(durationText); // This is safe to do now
        Flight flight = new Flight(departAirport, arriveAirport, duration, airline);
        centralRegistry.addFlight(flight);
        JOptionPane.showMessageDialog(this, "Flight added successfully!");
    }
}
