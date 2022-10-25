package pl.edu.pb.myapplicationquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private static final int Request_Code_Prompt = 0;
    private boolean answer_was_shown = false;
    private int currentIndex = 0;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
    private TextView questionTextView;
    private String Quiz_Tag = "Quiz_awesome_app";
    private String Key_Current_Index = "CurrentIndex";
    public static final String Key_extra_answer = "pl.edu.pb.myapplicationquiz.correctAnswer";

    private void CheckIfAnswerIsCorrect(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].GetAnswer();
        int resultMessageId = 0;
        if(answer_was_shown){
            resultMessageId = R.string.answer_was_shown;
        }
        else if(userAnswer == correctAnswer){
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
    protected void onStart() {
        super.onStart();
        Log.d(Quiz_Tag, "Została wywołana metoda onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Quiz_Tag, "Została wywołana metoda onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Quiz_Tag, "Została wywołana metoda onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Quiz_Tag, "Została wywołana metoda onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Quiz_Tag, "Została wywołana metoda onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Quiz_Tag, "Wywołana została metoda cyklu życia: OnCreate");
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(Key_Current_Index);
        }

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
                answer_was_shown = false;
                SetNextQuestion();
            }
        });

        promptButton = findViewById(R.id.hint_button);
        promptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PromptActivityJ.class);
                boolean correctAnswer = questions[currentIndex].GetAnswer();
                intent.putExtra(Key_extra_answer, correctAnswer);
                startActivityForResult(intent, Request_Code_Prompt);
            }
        });

        questionTextView = findViewById(R.id.question_text_view);

        SetNextQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(Quiz_Tag, "Wywołana została metoda onSaveInstanceState");
        outState.putInt(Key_Current_Index, currentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK)return;
        if(requestCode == Request_Code_Prompt){
            if(data ==null) return;
            answer_was_shown = data.getBooleanExtra(PromptActivityJ.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }
}