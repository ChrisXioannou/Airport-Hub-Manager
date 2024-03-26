package AirportHub;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchGui extends JFrame {

    private JTextField airportNameField;
    private JButton findButton, backButton;
    private JPanel mainPanel, buttonPanel;
    private CentralRegistry centralRegistry;

    public SearchGui(CentralRegistry centralRegistry) {
        this.centralRegistry = centralRegistry;

        airportNameField = new JTextField(15);
        findButton = new JButton("Search");
        backButton = new JButton("Back to Home");

        mainPanel = new JPanel();
        buttonPanel = new JPanel(new FlowLayout()); 

        mainPanel.setLayout(new GridLayout(4, 1)); 
        mainPanel.add(new JLabel("Starting City:")); 
        mainPanel.add(airportNameField);
        mainPanel.add(findButton);

        // Adding back button to its panel
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel); // Add the button panel to the main panel

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredCity = airportNameField.getText().trim();
                Airport airport = centralRegistry.getAirport(enteredCity);
                showMessage(airport, enteredCity);
            }
        });

        // Back button action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the current GUI and open the IntroGui
                dispose();
                new IntroGui(centralRegistry).setVisible(true);
            }
        });

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        setTitle("Starting City");
        setSize(300, 200); // Adjusted size for the new button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showMessage(Airport airport, String enteredCity) {
        if (airport == null) {
            JOptionPane.showMessageDialog(this,
                    "Sorry, unfortunately, we can't find your starting city.",
                    "Starting City Not Found",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            AirportGui airportPage = new AirportGui(centralRegistry, airport);
            airportPage.setVisible(true);
        }
    }
}
