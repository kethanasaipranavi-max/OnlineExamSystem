import java.sql.*;
import java.util.ArrayList;

public class Database {

    // ==========================================================
    // MYSQL CONFIGURATION
    // ==========================================================

    private static final String URL =
            "jdbc:mysql://localhost:3306/online_exam?useSSL=false&serverTimezone=UTC";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "pranavi.16";

    // ==========================================================
    // DATABASE CONNECTION
    // ==========================================================

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }

    // ==========================================================
    // TEST DATABASE CONNECTION
    // ==========================================================

    public static boolean testConnection() {

        try (Connection con = getConnection()) {

            return con != null && !con.isClosed();

        } catch (SQLException e) {

            System.out.println(
                    "Database connection failed!"
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );

            return false;
        }
    }

    // ==========================================================
    // LOGIN
    // ==========================================================

    public static String login(
            String username,
            String password) {

        String sql =
                "SELECT role FROM users " +
                "WHERE username = ? AND password = ?";

        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return rs.getString("role");
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Login database error:"
            );

            e.printStackTrace();
        }

        return null;
    }

    // ==========================================================
    // LOAD RANDOM QUESTIONS
    // ==========================================================

    public static ArrayList<Question>
    getRandomQuestions(
            String subject,
            int limit) {

        ArrayList<Question> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM questions " +
                "WHERE subject = ? " +
                "ORDER BY RAND() " +
                "LIMIT ?";

        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, subject);
            ps.setInt(2, limit);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    String[] options = {

                            rs.getString("option_a"),

                            rs.getString("option_b"),

                            rs.getString("option_c"),

                            rs.getString("option_d")
                    };

                    Question question =
                            new Question(

                                    rs.getInt("id"),

                                    rs.getString(
                                            "subject"
                                    ),

                                    rs.getString(
                                            "question_text"
                                    ),

                                    options,

                                    rs.getInt(
                                            "correct_answer"
                                    )
                            );

                    list.add(question);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Question loading error:"
            );

            e.printStackTrace();
        }

        return list;
    }

    // ==========================================================
    // SAVE EXAM RESULT
    // ==========================================================

    public static boolean saveResult(

            String username,
            String subject,
            int totalQuestions,
            int correct,
            int wrong,
            int unanswered,
            double score,
            double percentage,
            String result) {

        String sql =
                "INSERT INTO results " +
                "(username, subject, total_questions, " +
                "correct_answers, wrong_answers, " +
                "unanswered, score, percentage, result) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, username);

            ps.setString(2, subject);

            ps.setInt(3, totalQuestions);

            ps.setInt(4, correct);

            ps.setInt(5, wrong);

            ps.setInt(6, unanswered);

            ps.setDouble(7, score);

            ps.setDouble(8, percentage);

            ps.setString(9, result);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Result saving error:"
            );

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // RESULT HISTORY
    // ==========================================================

    public static ArrayList<String[]>
    getResultHistory() {

        ArrayList<String[]> history =
                new ArrayList<>();

        String sql =
                "SELECT username, subject, " +
                "score, percentage, result, exam_date " +
                "FROM results " +
                "ORDER BY exam_date DESC";

        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                String[] row = {

                        rs.getString(
                                "username"
                        ),

                        rs.getString(
                                "subject"
                        ),

                        String.format(
                                "%.2f",
                                rs.getDouble("score")
                        ),

                        String.format(
                                "%.2f%%",
                                rs.getDouble("percentage")
                        ),

                        rs.getString(
                                "result"
                        ),

                        rs.getString(
                                "exam_date"
                        )
                };

                history.add(row);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Result history error:"
            );

            e.printStackTrace();
        }

        return history;
    }

    // ==========================================================
    // ADD QUESTION - ADMIN
    // ==========================================================

    public static boolean addQuestion(

            String subject,
            String question,
            String optionA,
            String optionB,
            String optionC,
            String optionD,
            int correctAnswer) {

        String sql =
                "INSERT INTO questions " +
                "(subject, question_text, " +
                "option_a, option_b, option_c, option_d, " +
                "correct_answer) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, subject);

            ps.setString(2, question);

            ps.setString(3, optionA);

            ps.setString(4, optionB);

            ps.setString(5, optionC);

            ps.setString(6, optionD);

            ps.setInt(7, correctAnswer);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Question insertion error:"
            );

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // COUNT QUESTIONS
    // ==========================================================

    public static int countQuestions(
            String subject) {

        String sql =
                "SELECT COUNT(*) " +
                "FROM questions " +
                "WHERE subject = ?";

        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, subject);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Question count error:"
            );

            e.printStackTrace();
        }

        return 0;
    }

    // ==========================================================
    // GET TOTAL NUMBER OF QUESTIONS
    // ==========================================================

    public static int getTotalQuestions() {

        String sql =
                "SELECT COUNT(*) FROM questions";

        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Total question count error:"
            );

            e.printStackTrace();
        }

        return 0;
    }

    // ==========================================================
    // DELETE QUESTION - ADMIN
    // ==========================================================

    public static boolean deleteQuestion(
            int questionId) {

        String sql =
                "DELETE FROM questions " +
                "WHERE id = ?";

        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, questionId);

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Question deletion error:"
            );

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // CHECK WHETHER USER EXISTS
    // ==========================================================

    public static boolean userExists(
            String username) {

        String sql =
                "SELECT username FROM users " +
                "WHERE username = ?";

        try (
                Connection con = getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "User checking error:"
            );

            e.printStackTrace();

            return false;
        }
    }
}