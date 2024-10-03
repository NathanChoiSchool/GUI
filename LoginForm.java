import javax.swing.*;
import java.awt.*;

public class LoginForm {
    public static void displayLoginForm() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(1000, 1000);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(new GridBagLayout());

        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.insets = new Insets(10, 50, 10, 50);

        JTextField userField = new JTextField(20);
        JPasswordField passField = new JPasswordField(20);
        JButton submitButton = new JButton("Login");

        Font inputFont = new Font("Quicksand", Font.PLAIN, 24);
        userField.setFont(inputFont);
        passField.setFont(inputFont);
        submitButton.setFont(new Font("Quicksand", Font.BOLD, 14));

        userField.setBorder(BorderFactory.createEmptyBorder());
        passField.setBorder(BorderFactory.createEmptyBorder());

        submitButton.setBackground(new Color(200, 200, 200));
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);

        submitButton.addActionListener(e -> {
            String userName = userField.getText().trim();
            String userPassword = new String(passField.getPassword()).trim();

            if (userName.isEmpty() || userPassword.isEmpty()) {
                JOptionPane.showMessageDialog(loginFrame, "Both username and password fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                User loginUser = UserDatabase.getUser(userName);
                if (loginUser == null) {
                    JOptionPane.showMessageDialog(loginFrame, "Username does not exist.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                } else if (!loginUser.getPassword().equals(userPassword)) {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Login Successful! Welcome, " + loginUser.getFirstName() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    showUserProfile(loginUser);
                    loginFrame.dispose();
                }
            }
        });

        loginFrame.add(new JLabel("Username:"), layoutConstraints);
        loginFrame.add(userField, layoutConstraints);
        loginFrame.add(new JLabel("Password:"), layoutConstraints);
        loginFrame.add(passField, layoutConstraints);
        loginFrame.add(submitButton, layoutConstraints);

        loginFrame.setVisible(true);
    }

    private static void showUserProfile(User profileUser) {
        JFrame profileFrame = new JFrame("User Profile");
        profileFrame.setSize(400, 300);
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileFrame.setLocationRelativeTo(null);
        profileFrame.setLayout(new GridBagLayout());

        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.insets = new Insets(10, 10, 10, 10);

        JLabel firstNameLabel = new JLabel("First Name: " + profileUser.getFirstName());
        JLabel lastNameLabel = new JLabel("Last Name: " + profileUser.getLastName());
        JLabel emailLabel = new JLabel("Email: " + profileUser.getEmail());
        JLabel passwordLabel = new JLabel("Password: " + profileUser.getPassword());

        firstNameLabel.setFont(new Font("Quicksand", Font.PLAIN, 18));
        lastNameLabel.setFont(new Font("Quicksand", Font.PLAIN, 18));
        emailLabel.setFont(new Font("Quicksand", Font.PLAIN, 18));
        passwordLabel.setFont(new Font("Quicksand", Font.PLAIN, 18));

        profileFrame.add(firstNameLabel, layoutConstraints);
        profileFrame.add(lastNameLabel, layoutConstraints);
        profileFrame.add(emailLabel, layoutConstraints);
        profileFrame.add(passwordLabel, layoutConstraints);

        profileFrame.setVisible(true);
    }
}
