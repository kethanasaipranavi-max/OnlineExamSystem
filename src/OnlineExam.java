import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OnlineExam extends JFrame {

    // ==========================================================
    // USER
    // ==========================================================

    private String username;

    // ==========================================================
    // QUESTIONS
    // ==========================================================

    private ArrayList<Question> questions =
            new ArrayList<>();

    private int[] userAnswers;

    private int currentQuestion = 0;

    // ==========================================================
    // EXAM SETTINGS
    // ==========================================================

    private int timeLeft = 60;

    private final double NEGATIVE_MARK = 0.25;

    private final int MARK_PER_QUESTION = 1;

    // ==========================================================
    // GUI
    // ==========================================================

    private JPanel mainPanel;

    private JLabel questionLabel;
    private JLabel questionNumberLabel;
    private JLabel timerLabel;
    private JLabel subjectLabel;
    private JLabel statusLabel;

    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;

    private ButtonGroup group;

    private JPanel palettePanel;

    private Timer timer;

    // ==========================================================
    // COLORS
    // ==========================================================

    private final Color DARK =
            new Color(31, 41, 55);

    private final Color BLUE =
            new Color(37, 99, 235);

    private final Color GREEN =
            new Color(22, 163, 74);

    private final Color RED =
            new Color(220, 38, 38);

    // ==========================================================
    // CONSTRUCTOR
    // ==========================================================

    public OnlineExam(String username) {

        this.username = username;

        setTitle("Online Examination System");

        setSize(1050, 700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        showSubjectSelection();

        setVisible(true);
    }

    // ==========================================================
    // SUBJECT SELECTION
    // ==========================================================

    private void showSubjectSelection() {

        mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        JLabel title =
                new JLabel(
                        "SELECT EXAMINATION SUBJECT",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(DARK);

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        10,
                        20,
                        10
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel center =
                new JPanel(new GridBagLayout());

        center.setBackground(
                new Color(245, 247, 250)
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        15,
                        15,
                        15,
                        15
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        String[] subjects = {
                "Java Fundamentals",
                "Machine Learning",
                "Computer Networks",
                "Operating Systems"
        };

        for (int i = 0; i < subjects.length; i++) {

            JButton button =
                    new JButton(subjects[i]);

            button.setPreferredSize(
                    new Dimension(300, 60)
            );

            button.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            16
                    )
            );

            button.setFocusPainted(false);

            final String subject =
                    subjects[i];

            button.addActionListener(
                    e -> showInstructions(subject)
            );

            gbc.gridx = i % 2;
            gbc.gridy = i / 2;

            center.add(button, gbc);
        }

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );

        JLabel userLabel =
                new JLabel(
                        "Logged in as: " + username,
                        SwingConstants.CENTER
                );

        userLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        mainPanel.add(
                userLabel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    // ==========================================================
    // INSTRUCTIONS
    // ==========================================================

    private void showInstructions(String subject) {

        String instructions =
                "SUBJECT: "
                + subject
                + "\n\n"
                + "EXAM INSTRUCTIONS\n\n"
                + "1. 10 random questions will be selected.\n"
                + "2. Time limit is 60 seconds.\n"
                + "3. Each correct answer = +1 mark.\n"
                + "4. Each wrong answer = -0.25 mark.\n"
                + "5. Unanswered questions = 0 mark.\n"
                + "6. Passing percentage is 40%.\n"
                + "7. You can navigate between questions.\n"
                + "8. You can use the question palette.\n"
                + "9. Exam automatically submits when time ends.\n\n"
                + "Do you want to start?";

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        instructions,
                        "Exam Instructions",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE
                );

        if (choice == JOptionPane.YES_OPTION) {
            startExam(subject);
        }
    }

    // ==========================================================
    // START EXAM
    // ==========================================================

    private void startExam(String subject) {

        questions =
                Database.getRandomQuestions(
                        subject,
                        10
                );

        if (questions.size() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "No questions found for "
                    + subject
                    + ".\nPlease ask the administrator to add questions.",
                    "No Questions",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        userAnswers =
                new int[questions.size()];

        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = -1;
        }

        currentQuestion = 0;

        timeLeft = 60;

        createExamUI(subject);

        displayQuestion();

        startTimer();
    }

    // ==========================================================
    // EXAM UI
    // ==========================================================

    private void createExamUI(String subject) {

        mainPanel.removeAll();

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(DARK);

        JLabel userLabel =
                new JLabel(
                        "Student: " + username
                );

        userLabel.setForeground(Color.WHITE);

        userLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        15,
                        10,
                        10
                )
        );

        subjectLabel =
                new JLabel(
                        "Subject: " + subject,
                        SwingConstants.CENTER
                );

        subjectLabel.setForeground(Color.WHITE);

        subjectLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        timerLabel =
                new JLabel(
                        "Time: 60",
                        SwingConstants.RIGHT
                );

        timerLabel.setForeground(Color.WHITE);

        timerLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        timerLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        15
                )
        );

        header.add(
                userLabel,
                BorderLayout.WEST
        );

        header.add(
                subjectLabel,
                BorderLayout.CENTER
        );

        header.add(
                timerLabel,
                BorderLayout.EAST
        );

        // ------------------------------------------------------
        // QUESTION
        // ------------------------------------------------------

        questionNumberLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        questionNumberLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        questionNumberLabel.setForeground(BLUE);

        questionLabel =
                new JLabel();

        questionLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        questionLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JPanel questionPanel =
                new JPanel(new BorderLayout());

        questionPanel.setBackground(Color.WHITE);

        questionPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                210,
                                215,
                                220
                        )
                )
        );

        questionPanel.add(
                questionNumberLabel,
                BorderLayout.NORTH
        );

        questionPanel.add(
                questionLabel,
                BorderLayout.CENTER
        );

        // ------------------------------------------------------
        // OPTIONS
        // ------------------------------------------------------

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        JRadioButton[] options = {
                option1,
                option2,
                option3,
                option4
        };

        for (JRadioButton option : options) {

            option.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            17
                    )
            );

            option.setBackground(Color.WHITE);
        }

        group = new ButtonGroup();

        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        JPanel optionPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                5,
                                10
                        )
                );

        optionPanel.setBackground(Color.WHITE);

        optionPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        optionPanel.add(option1);
        optionPanel.add(option2);
        optionPanel.add(option3);
        optionPanel.add(option4);

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        center.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        center.add(
                questionPanel,
                BorderLayout.NORTH
        );

        center.add(
                optionPanel,
                BorderLayout.CENTER
        );

        // ------------------------------------------------------
        // PALETTE
        // ------------------------------------------------------

        JPanel paletteContainer =
                new JPanel(new BorderLayout());

        paletteContainer.setBackground(Color.WHITE);

        paletteContainer.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                210,
                                215,
                                220
                        )
                )
        );

        JLabel paletteTitle =
                new JLabel(
                        "QUESTIONS",
                        SwingConstants.CENTER
                );

        paletteTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        palettePanel =
                new JPanel(
                        new GridLayout(
                                5,
                                2,
                                5,
                                5
                        )
                );

        palettePanel.setBackground(Color.WHITE);

        for (int i = 0; i < questions.size(); i++) {

            final int index = i;

            JButton button =
                    new JButton("" + (i + 1));

            button.setFocusPainted(false);

            button.addActionListener(
                    e -> {

                        saveAnswer();

                        currentQuestion = index;

                        displayQuestion();
                    }
            );

            palettePanel.add(button);
        }

        paletteContainer.add(
                paletteTitle,
                BorderLayout.NORTH
        );

        paletteContainer.add(
                palettePanel,
                BorderLayout.CENTER
        );

        // ------------------------------------------------------
        // STATUS
        // ------------------------------------------------------

        statusLabel =
                new JLabel(
                        "Not answered",
                        SwingConstants.CENTER
                );

        // ------------------------------------------------------
        // BUTTONS
        // ------------------------------------------------------

        JButton previous =
                new JButton("← Previous");

        JButton next =
                new JButton("Next →");

        JButton clear =
                new JButton("Clear");

        JButton submit =
                new JButton("✓ Submit Exam");

        previous.addActionListener(
                e -> {

                    saveAnswer();

                    if (currentQuestion > 0) {

                        currentQuestion--;

                        displayQuestion();
                    }
                }
        );

        next.addActionListener(
                e -> {

                    saveAnswer();

                    if (
                            currentQuestion
                            < questions.size() - 1
                    ) {

                        currentQuestion++;

                        displayQuestion();
                    }
                }
        );

        clear.addActionListener(
                e -> {

                    group.clearSelection();

                    userAnswers[currentQuestion] = -1;

                    displayQuestion();
                }
        );

        submit.addActionListener(
                e -> {

                    saveAnswer();

                    int unanswered =
                            countUnanswered();

                    int choice =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Unanswered: "
                                    + unanswered
                                    + "\n\n"
                                    + "Do you want to submit?",
                                    "Submit Exam",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (
                            choice
                            == JOptionPane.YES_OPTION
                    ) {

                        submitExam();
                    }
                }
        );

        submit.setBackground(GREEN);

        submit.setForeground(Color.WHITE);

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                8
                        )
                );

        buttons.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        buttons.add(previous);
        buttons.add(clear);
        buttons.add(next);
        buttons.add(submit);

        JPanel bottom =
                new JPanel(new BorderLayout());

        bottom.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        bottom.add(
                statusLabel,
                BorderLayout.NORTH
        );

        bottom.add(
                buttons,
                BorderLayout.SOUTH
        );

        // ------------------------------------------------------
        // ADD
        // ------------------------------------------------------

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );

        mainPanel.add(
                paletteContainer,
                BorderLayout.EAST
        );

        mainPanel.add(
                bottom,
                BorderLayout.SOUTH
        );

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // ==========================================================
    // DISPLAY QUESTION
    // ==========================================================

    private void displayQuestion() {

        Question q =
                questions.get(currentQuestion);

        questionNumberLabel.setText(
                "Question "
                + (currentQuestion + 1)
                + " / "
                + questions.size()
        );

        questionLabel.setText(
                "<html><div style='width:650px'>"
                + q.getQuestionText()
                + "</div></html>"
        );

        String[] options =
                q.getOptions();

        option1.setText("A. " + options[0]);
        option2.setText("B. " + options[1]);
        option3.setText("C. " + options[2]);
        option4.setText("D. " + options[3]);

        group.clearSelection();

        if (userAnswers[currentQuestion] == 0) {

            option1.setSelected(true);

        } else if (userAnswers[currentQuestion] == 1) {

            option2.setSelected(true);

        } else if (userAnswers[currentQuestion] == 2) {

            option3.setSelected(true);

        } else if (userAnswers[currentQuestion] == 3) {

            option4.setSelected(true);
        }

        if (userAnswers[currentQuestion] == -1) {

            statusLabel.setText("Not answered");

        } else {

            statusLabel.setText("Answer selected");
        }

        updatePalette();
    }

    // ==========================================================
    // SAVE ANSWER
    // ==========================================================

    private void saveAnswer() {

        if (
                option1 != null
                && option1.isSelected()
        ) {

            userAnswers[currentQuestion] = 0;

        } else if (
                option2 != null
                && option2.isSelected()
        ) {

            userAnswers[currentQuestion] = 1;

        } else if (
                option3 != null
                && option3.isSelected()
        ) {

            userAnswers[currentQuestion] = 2;

        } else if (
                option4 != null
                && option4.isSelected()
        ) {

            userAnswers[currentQuestion] = 3;
        }
    }

    // ==========================================================
    // UPDATE PALETTE
    // ==========================================================

    private void updatePalette() {

        Component[] components =
                palettePanel.getComponents();

        for (int i = 0; i < components.length; i++) {

            JButton button =
                    (JButton) components[i];

            if (i == currentQuestion) {

                button.setBackground(BLUE);

                button.setForeground(Color.WHITE);

            } else if (userAnswers[i] != -1) {

                button.setBackground(GREEN);

                button.setForeground(Color.WHITE);

            } else {

                button.setBackground(Color.LIGHT_GRAY);

                button.setForeground(Color.BLACK);
            }
        }
    }

    // ==========================================================
    // COUNT UNANSWERED
    // ==========================================================

    private int countUnanswered() {

        int count = 0;

        for (int answer : userAnswers) {

            if (answer == -1) {
                count++;
            }
        }

        return count;
    }

    // ==========================================================
    // TIMER
    // ==========================================================

    private void startTimer() {

        timer =
                new Timer(
                        1000,
                        e -> {

                            timeLeft--;

                            timerLabel.setText(
                                    "Time: "
                                    + timeLeft
                            );

                            if (timeLeft <= 10) {

                                timerLabel.setForeground(
                                        RED
                                );
                            }

                            if (timeLeft <= 0) {

                                timer.stop();

                                saveAnswer();

                                JOptionPane.showMessageDialog(
                                        this,
                                        "Time is over!\n"
                                        + "Your exam will be submitted automatically.",
                                        "Time Over",
                                        JOptionPane.WARNING_MESSAGE
                                );

                                submitExam();
                            }
                        }
                );

        timer.start();
    }

    // ==========================================================
    // SUBMIT EXAM
    // ==========================================================

    private void submitExam() {

        if (timer != null) {
            timer.stop();
        }

        int correct = 0;
        int wrong = 0;
        int unanswered = 0;

        for (int i = 0; i < questions.size(); i++) {

            if (userAnswers[i] == -1) {

                unanswered++;

            } else if (
                    userAnswers[i]
                    == questions
                            .get(i)
                            .getCorrectAnswer()
            ) {

                correct++;

            } else {

                wrong++;
            }
        }

        // ======================================================
        // NEGATIVE MARKING
        // ======================================================

        double score =
                (correct * 1.0)
                -
                (wrong * NEGATIVE_MARK);

        double maximumMarks =
                questions.size();

        double percentage =
                (score / maximumMarks) * 100;

        if (percentage < 0) {
            percentage = 0;
        }

        String result;

        if (percentage >= 40) {
            result = "PASS";
        } else {
            result = "FAIL";
        }

        // ======================================================
        // GET SUBJECT
        // ======================================================

        String subject =
                subjectLabel
                        .getText()
                        .replace(
                                "Subject: ",
                                ""
                        );

        // ======================================================
        // SAVE TO DATABASE
        // ======================================================

        Database.saveResult(
                username,
                subject,
                questions.size(),
                correct,
                wrong,
                unanswered,
                score,
                percentage,
                result
        );

        // ======================================================
        // RESULT MESSAGE
        // ======================================================

        String message =
                "================================\n"
                + "          EXAM RESULT\n"
                + "================================\n\n"
                + "Student       : "
                + username
                + "\n"
                + "Subject       : "
                + subject
                + "\n\n"
                + "Total         : "
                + questions.size()
                + "\n"
                + "Correct       : "
                + correct
                + "\n"
                + "Wrong         : "
                + wrong
                + "\n"
                + "Unanswered    : "
                + unanswered
                + "\n\n"
                + String.format(
                        "Score         : %.2f / %d\n",
                        score,
                        questions.size()
                )
                + String.format(
                        "Percentage    : %.2f%%\n",
                        percentage
                )
                + "Result        : "
                + result
                + "\n\n"
                + "Correct: +1 mark\n"
                + "Wrong: -0.25 mark\n"
                + "Unanswered: 0 mark\n\n"
                + "================================";

        JOptionPane.showMessageDialog(
                this,
                message,
                "Final Result",
                JOptionPane.INFORMATION_MESSAGE
        );

        // ======================================================
        // SHOW CORRECT ANSWERS
        // ======================================================

        showAnswerReview();

        // Close exam window
        dispose();

        // Open subject selection again
        new OnlineExam(username);
    }

    // ==========================================================
    // VIEW ANSWERS
    // ==========================================================

    private void showAnswerReview() {

        JTextArea answerArea =
                new JTextArea();

        answerArea.setEditable(false);

        answerArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        answerArea.setLineWrap(true);

        answerArea.setWrapStyleWord(true);

        StringBuilder answers =
                new StringBuilder();

        answers.append(
                "================================================\n"
        );

        answers.append(
                "                 ANSWER REVIEW\n"
        );

        answers.append(
                "================================================\n\n"
        );

        answers.append(
                "Student: "
        );

        answers.append(username);

        answers.append("\n\n");

        for (int i = 0; i < questions.size(); i++) {

            Question q =
                    questions.get(i);

            String[] options =
                    q.getOptions();

            int correctAnswer =
                    q.getCorrectAnswer();

            int userAnswer =
                    userAnswers[i];

            answers.append(
                    "Question "
                    + (i + 1)
                    + ":\n"
            );

            answers.append(
                    q.getQuestionText()
            );

            answers.append("\n\n");

            // --------------------------------------------------
            // CORRECT ANSWER
            // --------------------------------------------------

            answers.append(
                    "Correct Answer: "
            );

            answers.append(
                    getAnswerText(
                            options,
                            correctAnswer
                    )
            );

            answers.append("\n");

            // --------------------------------------------------
            // USER ANSWER
            // --------------------------------------------------

            answers.append(
                    "Your Answer: "
            );

            if (userAnswer == -1) {

                answers.append(
                        "Not Answered"
                );

            } else {

                answers.append(
                        getAnswerText(
                                options,
                                userAnswer
                        )
                );
            }

            answers.append("\n");

            // --------------------------------------------------
            // STATUS
            // --------------------------------------------------

            if (userAnswer == -1) {

                answers.append(
                        "Status: UNANSWERED"
                );

            } else if (userAnswer == correctAnswer) {

                answers.append(
                        "Status: CORRECT"
                );

            } else {

                answers.append(
                        "Status: WRONG"
                );
            }

            answers.append(
                    "\n"
            );

            answers.append(
                    "------------------------------------------------\n\n"
            );
        }

        answerArea.setText(
                answers.toString()
        );

        JScrollPane scrollPane =
                new JScrollPane(answerArea);

        scrollPane.setPreferredSize(
                new Dimension(
                        750,
                        550
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "View Correct Answers",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==========================================================
    // GET ANSWER TEXT
    // ==========================================================

    private String getAnswerText(
            String[] options,
            int answerIndex
    ) {

        if (answerIndex < 0 || answerIndex >= options.length) {
            return "Unknown";
        }

        String letter;

        switch (answerIndex) {

            case 0:
                letter = "A";
                break;

            case 1:
                letter = "B";
                break;

            case 2:
                letter = "C";
                break;

            case 3:
                letter = "D";
                break;

            default:
                letter = "?";
        }

        return letter
                + ". "
                + options[answerIndex];
    }
}