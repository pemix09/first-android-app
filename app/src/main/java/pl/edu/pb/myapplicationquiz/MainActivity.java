package pl.edu.pb.myapplicationquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Question[] questions = new Question[]{
            new Question(R.string.q_activity, true),
            new Question(R.string.q_find_resources, false),
            new Question(R.string.q_listener, true),
            new Question(R.string.q_resources, true),
            new Question(R.string.q_version, false)
    };
    private int currentIndex = 0;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private void CheckIfAnswerIsCorrect(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].GetAnswer();
        int resultMessageId = 0;
        if(userAnswer == correctAnswer){
            resultMessageId = R.string.corrent_answer;
        }
        else{
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    private void SetNextQuestion(){
        questionTextView.setText(questions[currentIndex].GetQuestionId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                CheckIfAnswerIsCorrect(true);
            }
        });

        falseButton = findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                CheckIfAnswerIsCorrect(false);
            }
        });

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                currentIndex = (currentIndex + 1)%questions.length;
                SetNextQuestion();
            }
        });

        questionTextView = findViewById(R.id.question_text_view);

        SetNextQuestion();
    }
}