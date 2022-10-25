package pl.edu.pb.myapplicationquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PromptActivityJ extends AppCompatActivity {

    private boolean correctAnswer;
    private Button showCorrectAnswer;
    private TextView answerTextView;
    public static final String KEY_EXTRA_ANSWER_SHOWN = "pl.edu.pb.answerShown";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        correctAnswer = getIntent().getBooleanExtra(MainActivity.Key_extra_answer, true);
        showCorrectAnswer = findViewById(R.id.hint_button);
        showCorrectAnswer.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v){
                int answer = correctAnswer ? R.string.button_true : R.string.button_false;
                answerTextView.setText(answer);
                setAnswerShownResult(true);
            }
        });

        answerTextView = findViewById(R.id.Answer_text_view);
    }
    private void setAnswerShownResult(boolean answerWasShown){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
        setResult(RESULT_OK, resultIntent);
    }
}
