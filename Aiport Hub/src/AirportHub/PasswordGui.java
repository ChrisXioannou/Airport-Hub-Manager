package AirportHub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordGui extends JDialog {
    private JPasswordField passwordField;
    private JButton submitButton;

    public PasswordGui(CentralRegistry centralRegistry) {

        passwordField = new JPasswordField(10);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                if (password.equals("1234")) {
                    // Correct password
                    dispose(); // Close the password dialog
                    new AdminGui(centralRegistry); // Open the admin GUI
                
                    
                } else {
                    // Incorrect password
                    JOptionPane.showMessageDialog(PasswordGui.this,
                            "Incorrect password.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                }
            }
        });

        add(new JLabel("Enter Password:"));
        add(passwordField);
        add(submitButton);
        
        setLayout(new FlowLayout());
        setSize(250, 100);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
}

