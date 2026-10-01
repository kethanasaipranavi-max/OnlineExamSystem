import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;

    private JPasswordField passwordField;

    private JComboBox<String> roleBox;

    public LoginFrame() {

        setTitle(
                "Online Examination System - Login"
        );

        setSize(600, 450);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createLoginUI();

        setVisible(true);
    }

    // ==========================================================
    // LOGIN UI
    // ==========================================================

    private void createLoginUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        // ------------------------------------------------------
        // HEADER
        // ------------------------------------------------------

        JPanel header =
                new JPanel();

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        header.setBackground(
                new Color(
                        31,
                        41,
                        55
                )
        );

        JLabel title =
                new JLabel(
                        "ONLINE EXAMINATION SYSTEM"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitle =
                new JLabel(
                        "Secure Login Portal"
                );

        subtitle.setForeground(
                Color.WHITE
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        header.add(
                Box.createVerticalStrut(30)
        );

        header.add(title);

        header.add(
                Box.createVerticalStrut(8)
        );

        header.add(subtitle);

        header.add(
                Box.createVerticalStrut(30)
        );

        // ------------------------------------------------------
        // LOGIN PANEL
        // ------------------------------------------------------

        JPanel loginPanel =
                new JPanel(
                        new GridBagLayout()
                );

        loginPanel.setBackground(
                Color.WHITE
        );

        loginPanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        215,
                                        220
                                )
                        ),

                        BorderFactory.createEmptyBorder(
                                25,
                                40,
                                25,
                                40
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8,
                        8,
                        8,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // ------------------------------------------------------
        // USERNAME
        // ------------------------------------------------------

        JLabel usernameLabel =
                new JLabel(
                        "Username:"
                );

        usernameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        usernameField =
                new JTextField(
                        20
                );

        usernameField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        loginPanel.add(
                usernameLabel,
                gbc
        );

        gbc.gridx = 1;

        loginPanel.add(
                usernameField,
                gbc
        );

        // ------------------------------------------------------
        // PASSWORD
        // ------------------------------------------------------

        JLabel passwordLabel =
                new JLabel(
                        "Password:"
                );

        passwordLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        passwordField =
                new JPasswordField(
                        20
                );

        passwordField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 1;

        loginPanel.add(
                passwordLabel,
                gbc
        );

        gbc.gridx = 1;

        loginPanel.add(
                passwordField,
                gbc
        );

        // ------------------------------------------------------
        // ROLE
        // ------------------------------------------------------

        JLabel roleLabel =
                new JLabel(
                        "Login As:"
                );

        roleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        roleBox =
                new JComboBox<>(
                        new String[]{
                                "Student",
                                "Admin"
                        }
                );

        roleBox.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 2;

        loginPanel.add(
                roleLabel,
                gbc
        );

        gbc.gridx = 1;

        loginPanel.add(
                roleBox,
                gbc
        );

        // ------------------------------------------------------
        // LOGIN BUTTON
        // ------------------------------------------------------

        JButton loginButton =
                new JButton(
                        "LOGIN"
                );

        loginButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        loginButton.setBackground(
                new Color(
                        37,
                        99,
                        235
                )
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setFocusPainted(
                false
        );

        gbc.gridx = 0;

        gbc.gridy = 3;

        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(
                        20,
                        8,
                        8,
                        8
                );

        loginPanel.add(
                loginButton,
                gbc
        );

        // ------------------------------------------------------
        // LOGIN EVENT
        // ------------------------------------------------------

        loginButton.addActionListener(
                e -> login()
        );

        // ------------------------------------------------------
        // FOOTER
        // ------------------------------------------------------

        JLabel footer =
                new JLabel(
                        "Online Examination System",
                        SwingConstants.CENTER
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        mainPanel.add(
                loginPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    // ==========================================================
    // LOGIN
    // ==========================================================

    private void login() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        String selectedRole =
                roleBox
                        .getSelectedItem()
                        .toString();

        if (
                username.isEmpty()
                ||
                password.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String databaseRole =
                Database.login(
                        username,
                        password
                );

        if (
                databaseRole == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (
                selectedRole.equals("Admin")
                &&
                !databaseRole.equals("ADMIN")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "This account is not an administrator.",
                    "Access Denied",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (
                selectedRole.equals("Student")
                &&
                !databaseRole.equals("STUDENT")
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select the correct login role.",
                    "Access Denied",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        dispose();

        if (
                databaseRole.equals("ADMIN")
        ) {

            new AdminPanel(username);

        } else {

            new OnlineExam(
                    username
            ); 
        }
    }

    // ==========================================================
    // MAIN
    // ==========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                LoginFrame::new
        );
    }
}
