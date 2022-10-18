package pl.edu.pb.myapplicationquiz;

public class Question {
    private int questionId;
    private boolean trueAnswer;

    public Question(int questionId, boolean trueAnswer){
        this.questionId = questionId;
        this.trueAnswer = trueAnswer;
    }

    public boolean GetAnswer(){
        return this.trueAnswer;
    }

    public int GetQuestionId(){
        return this.questionId;
    }
}
