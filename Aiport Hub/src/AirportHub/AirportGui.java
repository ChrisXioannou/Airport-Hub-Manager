package AirportHub;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AirportGui extends JFrame {

    private JTextField CountryField, NameField, CityField, CodeField, AirportSearchField;
    private JButton FindFlightsButton, BackButton;
    private CentralRegistry centralRegistry;

    public AirportGui(CentralRegistry centralRegistry, Airport airport) {

        this.centralRegistry = centralRegistry;

        NameField = new JTextField(airport.getName());
        CodeField = new JTextField(airport.getCodedname());
        CityField = new JTextField(airport.getCity());
        CountryField = new JTextField(airport.getCountry());
        
        // Make the text fields uneditable
        NameField.setEditable(false);
        CodeField.setEditable(false);
        CityField.setEditable(false);
        CountryField.setEditable(false);

        AirportSearchField = new JTextField();
        AirportSearchField.setPreferredSize(new Dimension(150, 25));
        FindFlightsButton = new JButton("Find Flights");
        
        // Layout for airport information
        JPanel airportInfoPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        airportInfoPanel.add(new JLabel("Airport Name:"));
        airportInfoPanel.add(NameField);
        airportInfoPanel.add(new JLabel("Airport Code:"));
        airportInfoPanel.add(CodeField);
        airportInfoPanel.add(new JLabel("City:"));
        airportInfoPanel.add(CityField);
        airportInfoPanel.add(new JLabel("Country:"));
        airportInfoPanel.add(CountryField);
        
        // Panel for finding flights
        JPanel findFlightsPanel = new JPanel();
        findFlightsPanel.add(new JLabel("Enter the city you want to visit:"));
        findFlightsPanel.add(AirportSearchField);
        findFlightsPanel.add(FindFlightsButton);

        // Back button to return to search
        BackButton = new JButton("Back to Search Screen");

        // Main panel layout
        setLayout(new BorderLayout(10, 10));
     // ... continued from above

        add(airportInfoPanel, BorderLayout.NORTH);
        add(findFlightsPanel, BorderLayout.CENTER);
        add(BackButton, BorderLayout.PAGE_END);

        FindFlightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destinationCity = AirportSearchField.getText().trim();
                Airport destinationAirport = centralRegistry.getAirport(destinationCity);

                if (destinationAirport == null) {
                    JOptionPane.showMessageDialog(AirportGui.this,
                            "Destination airport not found.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (destinationCity.equals(airport.getCity())) {
                    JOptionPane.showMessageDialog(AirportGui.this,
                            "Arrival and departure city cannot be the same!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    FlightsGui flightsFrame = new FlightsGui(centralRegistry, airport, destinationAirport);
                    flightsFrame.setVisible(true);
                }
            }
        });

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            SearchGui frame = new SearchGui(centralRegistry);
                            frame.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });

        setTitle("Airport Information");
        pack(); // Adjust the window size to fit the content
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

