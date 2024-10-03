import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.util.Random;

class SubmitButtonListener implements ActionListener {
    JFrame signupFrame;
    JTextField firstNameField;
    JTextField lastNameField;
    JTextField emailField;
    JPasswordField passwordField;

    private static void validatePassword(String password) throws PasswordException {
        if (password.length() < 8) throw new Minimum8CharactersRequired();
        if (!password.matches(".*\\d.*")) throw new NumberCharacterMissing();
        if (!password.matches(".*[a-z].*")) throw new LowerCaseCharacterMissing();
        if (!password.matches(".*[A-Z].*")) throw new UpperCaseCharacterMissing();
        if (!password.matches(".*[!@#$%^&*()].*")) throw new SpecialCharacterMissing();
    }

    private static String generateUsername(String firstName, String lastName) {
        Random randomGenerator = new Random();
        int uniqueNumber = 1000 + randomGenerator.nextInt(9000);
        return "" + firstName.charAt(0) + lastName.charAt(0) + "-" + uniqueNumber;
    }

    public SubmitButtonListener(JFrame frame, JTextField firstName, JTextField lastName, JTextField email, JPasswordField password) {
        this.signupFrame = frame;
        this.firstNameField = firstName;
        this.lastNameField = lastName;
        this.emailField = email;
        this.passwordField = password;
    }

    public void actionPerformed(ActionEvent event) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(signupFrame, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                validatePassword(password);
                String username = generateUsername(firstName, lastName);
                User newUser = new User(firstName, lastName, email, password);
                UserDatabase.addUser(username, newUser);
                JOptionPane.showMessageDialog(signupFrame, "Signup Successful! Your username is: " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
                signupFrame.dispose();
            } catch (PasswordException exception) {
                JOptionPane.showMessageDialog(signupFrame, exception.getMessage(), "Signup Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

public class SignupForm {
    public static void displaySignupForm() {
        JFrame signupFrame = new JFrame("Signup");
        JTextField firstNameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        signupFrame.setSize(1000, 1000);
        signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signupFrame.setLocationRelativeTo(null);
        signupFrame.setLayout(new GridBagLayout());

        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.insets = new Insets(10, 30, 10, 30);

        JButton submitButton = new JButton("Submit");

        Font inputFieldFont = new Font("Quicksand", Font.PLAIN, 24);
        firstNameField.setFont(inputFieldFont);
        lastNameField.setFont(inputFieldFont);
        emailField.setFont(inputFieldFont);
        passwordField.setFont(inputFieldFont);
        submitButton.setFont(new Font("Quicksand", Font.BOLD, 14));

        SubmitButtonListener submitListener = new SubmitButtonListener(
            signupFrame, firstNameField, lastNameField, emailField, passwordField);
        submitButton.addActionListener(submitListener);

        signupFrame.add(new JLabel("First Name:"), layoutConstraints);
        signupFrame.add(firstNameField, layoutConstraints);
        signupFrame.add(new JLabel("Last Name:"), layoutConstraints);
        signupFrame.add(lastNameField, layoutConstraints);
        signupFrame.add(new JLabel("Email:"), layoutConstraints);
        signupFrame.add(emailField, layoutConstraints);
        signupFrame.add(new JLabel("Password:"), layoutConstraints);
        signupFrame.add(passwordField, layoutConstraints);
        signupFrame.add(submitButton, layoutConstraints);

        signupFrame.setVisible(true);
    }
}
