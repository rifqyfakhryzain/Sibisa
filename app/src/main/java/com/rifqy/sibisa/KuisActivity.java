package com.rifqy.sibisa;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class KuisActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private ImageView imgQuestion;
    private Button btnOption1, btnOption2, btnOption3;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);

        tvQuestion = findViewById(R.id.tvQuestion);
        imgQuestion = findViewById(R.id.imgQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        ImageView btnBack = findViewById(R.id.btnBack);

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> onBackPressed());
        }

        initQuestions();
        showQuestion();

        btnOption1.setOnClickListener(v -> checkAnswer(0));
        btnOption2.setOnClickListener(v -> checkAnswer(1));
        btnOption3.setOnClickListener(v -> checkAnswer(2));
    }

    private void initQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("Buah apakah ini?", R.drawable.apel, 
                new String[]{"Apel", "Salak", "Mangga"}, 0));
        questionList.add(new Question("Buah apakah ini?", R.drawable.jeruk, 
                new String[]{"Pisang", "Jeruk", "Apel"}, 1));
        questionList.add(new Question("Buah apakah ini?", R.drawable.pisang, 
                new String[]{"Pisang", "Salak", "Mangga"}, 0));
        questionList.add(new Question("Buah apakah ini?", R.drawable.mangga, 
                new String[]{"Jeruk", "Apel", "Mangga"}, 2));
        questionList.add(new Question("Buah apakah ini?", R.drawable.salak, 
                new String[]{"Salak", "Pisang", "Jeruk"}, 0));
    }

    private void showQuestion() {
        if (currentQuestionIndex < questionList.size()) {
            Question q = questionList.get(currentQuestionIndex);
            tvQuestion.setText(q.getQuestion());
            imgQuestion.setImageResource(q.getImageResId());
            btnOption1.setText(q.getOptions()[0]);
            btnOption2.setText(q.getOptions()[1]);
            btnOption3.setText(q.getOptions()[2]);
        } else {
            showScore();
        }
    }

    private void checkAnswer(int selectedIndex) {
        if (selectedIndex == questionList.get(currentQuestionIndex).getCorrectAnswerIndex()) {
            score++;
            Toast.makeText(this, "Benar!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Salah!", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        showQuestion();
    }

    private void showScore() {
        tvQuestion.setText("Kuis Selesai!\nSkor Anda: " + score + "/" + questionList.size());
        btnOption1.setVisibility(android.view.View.GONE);
        btnOption2.setVisibility(android.view.View.GONE);
        btnOption3.setVisibility(android.view.View.GONE);
        
        Button btnReset = new Button(this);
        btnReset.setText("Ulangi Kuis");
        btnReset.setBackgroundTintList(android.content.res.ColorStateList.valueOf(getResources().getColor(android.R.color.holo_green_dark)));
        // Add more styling or just keep it simple for now
        // For a better UI, I should probably have a results layout
    }

    private static class Question {
        private String question;
        private int imageResId;
        private String[] options;
        private int correctAnswerIndex;

        public Question(String question, int imageResId, String[] options, int correctAnswerIndex) {
            this.question = question;
            this.imageResId = imageResId;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getQuestion() { return question; }
        public int getImageResId() { return imageResId; }
        public String[] getOptions() { return options; }
        public int getCorrectAnswerIndex() { return correctAnswerIndex; }
    }
}
