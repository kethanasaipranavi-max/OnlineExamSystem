import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminPanel extends JFrame {

    private JTable resultTable;

    private DefaultTableModel tableModel;

    public AdminPanel(
            String adminUsername
    ) {

        setTitle(
                "Admin Panel - Online Examination System"
        );

        setSize(
                1000,
                650
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createUI(
                adminUsername
        );

        setVisible(true);
    }

    // ==========================================================
    // UI
    // ==========================================================

    private void createUI(
            String adminUsername
    ) {

        JPanel main =
                new JPanel(
                        new BorderLayout()
                );

        main.setBackground(
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
                new JPanel(
                        new BorderLayout()
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
                        "ADMIN DASHBOARD",
                        SwingConstants.CENTER
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

        JLabel user =
                new JLabel(
                        "Admin: "
                        + adminUsername
                );

        user.setForeground(
                Color.WHITE
        );

        user.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        15,
                        10,
                        10
                )
        );

        header.add(
                user,
                BorderLayout.WEST
        );

        header.add(
                title,
                BorderLayout.CENTER
        );

        // ------------------------------------------------------
        // TABLE
        // ------------------------------------------------------

        String[] columns = {

                "Username",

                "Subject",

                "Score",

                "Percentage",

                "Result",

                "Date"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                );

        resultTable =
                new JTable(
                        tableModel
                );

        resultTable.setRowHeight(
                28
        );

        resultTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        resultTable
                );

        // ------------------------------------------------------
        // BUTTONS
        // ------------------------------------------------------

        JButton refresh =
                new JButton(
                        "Refresh Results"
                );

        JButton addQuestion =
                new JButton(
                        "Add Question"
                );

        JButton logout =
                new JButton(
                        "Logout"
                );

        refresh.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        addQuestion.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        logout.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        addQuestion.setBackground(
                new Color(
                        22,
                        163,
                        74
                )
        );

        addQuestion.setForeground(
                Color.WHITE
        );

        logout.setBackground(
                new Color(
                        220,
                        38,
                        38
                )
        );

        logout.setForeground(
                Color.WHITE
        );

        JPanel buttons =
                new JPanel();

        buttons.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        buttons.add(refresh);

        buttons.add(addQuestion);

        buttons.add(logout);

        // ------------------------------------------------------
        // EVENTS
        // ------------------------------------------------------

        refresh.addActionListener(
                e -> loadResults()
        );

        addQuestion.addActionListener(
                e -> showAddQuestionDialog()
        );

        logout.addActionListener(
                e -> {

                    dispose();

                    new LoginFrame();
                }
        );

        // ------------------------------------------------------
        // ADD
        // ------------------------------------------------------

        main.add(
                header,
                BorderLayout.NORTH
        );

        main.add(
                scrollPane,
                BorderLayout.CENTER
        );

        main.add(
                buttons,
                BorderLayout.SOUTH
        );

        add(main);

        loadResults();
    }

    // ==========================================================
    // LOAD RESULTS
    // ==========================================================

    private void loadResults() {

        tableModel.setRowCount(0);

        ArrayList<String[]> history =
                Database.getResultHistory();

        for (
                String[] row :
                history
        ) {

            tableModel.addRow(
                    row
            );
        }
    }

    // ==========================================================
    // ADD QUESTION DIALOG
    // ==========================================================

    private void showAddQuestionDialog() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                8,
                                2,
                                8,
                                8
                        )
                );

        String[] subjects = {

                "Java Fundamentals",

                "Machine Learning",

                "Computer Networks",

                "Operating Systems"
        };

        JComboBox<String> subject =
                new JComboBox<>(
                        subjects
                );

        JTextField question =
                new JTextField();

        JTextField optionA =
                new JTextField();

        JTextField optionB =
                new JTextField();

        JTextField optionC =
                new JTextField();

        JTextField optionD =
                new JTextField();

        JComboBox<String> correct =
                new JComboBox<>(
                        new String[]{
                                "A",
                                "B",
                                "C",
                                "D"
                        }
                );

        panel.add(
                new JLabel("Subject:")
        );

        panel.add(subject);

        panel.add(
                new JLabel("Question:")
        );

        panel.add(question);

        panel.add(
                new JLabel("Option A:")
        );

        panel.add(optionA);

        panel.add(
                new JLabel("Option B:")
        );

        panel.add(optionB);

        panel.add(
                new JLabel("Option C:")
        );

        panel.add(optionC);

        panel.add(
                new JLabel("Option D:")
        );

        panel.add(optionD);

        panel.add(
                new JLabel("Correct Answer:")
        );

        panel.add(correct);

        int choice =
                JOptionPane.showConfirmDialog(

                        this,

                        panel,

                        "Add New Question",

                        JOptionPane.OK_CANCEL_OPTION,

                        JOptionPane.PLAIN_MESSAGE
                );

        if (
                choice !=
                JOptionPane.OK_OPTION
        ) {

            return;
        }

        if (
                question.getText()
                        .trim()
                        .isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Question cannot be empty."
            );

            return;
        }

        int correctIndex =
                correct.getSelectedIndex();

        boolean success =
                Database.addQuestion(

                        subject
                                .getSelectedItem()
                                .toString(),

                        question
                                .getText()
                                .trim(),

                        optionA
                                .getText()
                                .trim(),

                        optionB
                                .getText()
                                .trim(),

                        optionC
                                .getText()
                                .trim(),

                        optionD
                                .getText()
                                .trim(),

                        correctIndex
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Question added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add question.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
