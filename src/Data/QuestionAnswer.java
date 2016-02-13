package Data;

/**
 * Created by Cabbibi on 10/02/2016.
 */
public class QuestionAnswer {
    private String Question;
    private String Answers;

    public QuestionAnswer(String question, String answers) {
        Question = question;
        Answers = answers;
    }

    public String getQuestion() {
        return Question;
    }

    public String getAnswers() {
        return Answers;
    }
}
