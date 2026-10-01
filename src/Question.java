public class Question {

    private int id;
    private String subject;
    private String questionText;
    private String[] options;
    private int correctAnswer;

    // Constructor
    public Question(
            int id,
            String subject,
            String questionText,
            String[] options,
            int correctAnswer) {

        this.id = id;
        this.subject = subject;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}