import javax.swing.*;
import java.awt.*;

public class LoginForm {
    public static void displayLoginForm() {
        JFrame frame = new JFrame("Login");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        Font fieldFont = new Font("Arial", Font.PLAIN, 24);
        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        usernameField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setBorder(BorderFactory.createEmptyBorder());

        loginButton.setBackground(new Color(200, 200, 200));
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Both username and password fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                User user = UserDatabase.getUser(username);
                if (user == null) {
                    JOptionPane.showMessageDialog(frame, "Username does not exist.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                } else if (!user.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Invalid password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Login Successful! Welcome, " + user.getFirstName() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayUserProfile(user); // Display user profile upon successful login
                    frame.dispose(); // Optionally close the window upon successful login
                }
            }
        });

        frame.add(new JLabel("Username:"), gbc);
        frame.add(usernameField, gbc);
        frame.add(new JLabel("Password:"), gbc);
        frame.add(passwordField, gbc);
        frame.add(loginButton, gbc);

        frame.setVisible(true);
    }

    private static void displayUserProfile(User user) {
        JFrame profileFrame = new JFrame("User Profile");
        profileFrame.setSize(400, 300);
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileFrame.setLocationRelativeTo(null);
        profileFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel firstNameLabel = new JLabel("First Name: " + user.getFirstName());
        JLabel lastNameLabel = new JLabel("Last Name: " + user.getLastName());
        JLabel emailLabel = new JLabel("Email: " + user.getEmail());
        JLabel passwordLabel = new JLabel("Password: " + user.getPassword());

        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        profileFrame.add(firstNameLabel, gbc);
        profileFrame.add(lastNameLabel, gbc);
        profileFrame.add(emailLabel, gbc);
        profileFrame.add(passwordLabel, gbc);

        profileFrame.setVisible(true);
    }
}
