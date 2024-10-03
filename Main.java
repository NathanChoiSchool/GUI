import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("My Social Network");
            mainFrame.setSize(1000, 1000);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setLayout(new GridBagLayout());

            GridBagConstraints layoutConstraints = new GridBagConstraints();
            layoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
            layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
            layoutConstraints.insets = new Insets(10, 50, 10, 50);

            JLabel welcomeLabel = new JLabel("WELCOME!");
            welcomeLabel.setFont(new Font("Quicksand", Font.BOLD, 24));
            welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JButton signupButton = new JButton("Signup");
            JButton loginButton = new JButton("Login");

            signupButton.setFont(new Font("Quicksand", Font.BOLD, 14));
            loginButton.setFont(new Font("Quicksand", Font.BOLD, 14));

            signupButton.addActionListener(e -> SignupForm.displaySignupForm());
            loginButton.addActionListener(e -> LoginForm.displayLoginForm());

            mainFrame.add(welcomeLabel, layoutConstraints);
            mainFrame.add(signupButton, layoutConstraints);
            mainFrame.add(loginButton, layoutConstraints);

            mainFrame.setVisible(true);
        });
    }
}
