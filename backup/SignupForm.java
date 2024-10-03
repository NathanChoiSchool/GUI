import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent ;
import java.awt.event.* ;
import java.util.Random;

class SubmitButtonListener implements  ActionListener {
	JFrame frame ;
    JTextField firstNameField ;  
    JTextField lastNameField ; 
    JTextField emailField ; 
    JPasswordField passwordField ;

    private static void validatePassword(String password) throws PasswordException {
        if (password.length() < 8) throw new Minimum8CharactersRequired();
        if (!password.matches(".*\\d.*")) throw new NumberCharacterMissing();
        if (!password.matches(".*[a-z].*")) throw new LowerCaseCharacterMissing();
        if (!password.matches(".*[A-Z].*")) throw new UpperCaseCharacterMissing();
        if (!password.matches(".*[!@#$%^&*()].*")) throw new SpecialCharacterMissing();
    }

    private static String generateUsername(String firstName, String lastName) {
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);  // Generates a number from 1000 to 9999
        return "" + firstName.charAt(0) + lastName.charAt(0) + "-" + randomNumber;
    }
	public SubmitButtonListener ( JFrame frame, JTextField f , JTextField l , JTextField e , JPasswordField p ) {
			this.frame = frame;
    		firstNameField = f;  
    		lastNameField =  l; 
    		emailField =  e; 
    		passwordField = p ;
	}
	public void actionPerformed(ActionEvent e) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
         } else {
                try {
                    validatePassword(password);
                    String username = generateUsername(firstName, lastName);
                    User newUser = new User(firstName, lastName, email, password);
                    UserDatabase.addUser(username, newUser);
                    JOptionPane.showMessageDialog(frame, "Signup Successful! Your username is: " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } catch (PasswordException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Signup Error", JOptionPane.ERROR_MESSAGE);
                }
         }
	}
}

public class SignupForm {
    public static void displaySignupForm() {
    	JFrame frame = new JFrame("Signup");
    	JTextField firstNameField = new JTextField(20);
    	JTextField lastNameField = new JTextField(20);
    	JTextField emailField = new JTextField(20);
    	JPasswordField passwordField = new JPasswordField(20);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 30, 10, 30);

        JButton submitButton = new JButton("Submit");

        Font fieldFont = new Font("Arial", Font.PLAIN, 24);
        firstNameField.setFont(fieldFont);
        lastNameField.setFont(fieldFont);
        emailField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));

    	SubmitButtonListener submitButtonListener =  new  SubmitButtonListener(
			frame, firstNameField, lastNameField, emailField, passwordField ) ;
        submitButton.addActionListener( submitButtonListener );

        frame.add(new JLabel("First Name:"), gbc);
        frame.add(firstNameField, gbc);
        frame.add(new JLabel("Last Name:"), gbc);
        frame.add(lastNameField, gbc);
        frame.add(new JLabel("Email:"), gbc);
        frame.add(emailField, gbc);
        frame.add(new JLabel("Password:"), gbc);
        frame.add(passwordField, gbc);
        frame.add(submitButton, gbc);

        frame.setVisible(true);
    }

}
