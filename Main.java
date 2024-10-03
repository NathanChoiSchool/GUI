import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My Social Network");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);  // Centers the frame on the screen
            frame.setLayout(new GridBagLayout());  // Uses GridBagLayout for layout

            GridBagConstraints gbc = new GridBagConstraints();
			// xxx specify that the component's display area will be from gridx to 
			// xxx the last cell in the row
            gbc.gridwidth = GridBagConstraints.REMAINDER;
			// xxx Make the component wide enough to fill its display area horizontally, 
			// xxx but do not change its height.
            gbc.fill =      GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 50, 10, 50);  // Padding around components

            JLabel welcomeLabel = new JLabel("WELCOME!");
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Larger font size for welcome message
            welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JButton signupButton = new JButton("Signup");
            JButton loginButton = new JButton("Login");
            JButton exitButton = new JButton("Exit");

            // Set some styling
            signupButton.setFont(new Font("Arial", Font.BOLD, 14));
            loginButton.setFont(new Font("Arial", Font.BOLD, 14));

            // Adding action listeners to buttons using Lamda Expressions
            signupButton.addActionListener(e -> SignupForm.displaySignupForm());
            loginButton.addActionListener(e  -> LoginForm.displayLoginForm());

            frame.add(welcomeLabel, gbc);  // Add welcome label with constraints
            frame.add(signupButton, gbc);  // Add signup button with constraints
            frame.add(loginButton,  gbc);   // Add login button with constraints
            frame.add(exitButton,  gbc);   // Add exitButton button with constraints

            frame.setVisible(true);
        });
    }
}
