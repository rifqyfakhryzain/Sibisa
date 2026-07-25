package com.rifqy.sibisa;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import android.app.Dialog;
import android.view.Window;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.content.Intent;
import android.widget.ProgressBar;


public class KuisActivity extends AppCompatActivity {
    private TextView tvProgress;
    private ProgressBar progressQuiz;
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
        tvProgress = findViewById(R.id.tvProgress);
        progressQuiz = findViewById(R.id.progressQuiz);
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

            // Update Progress
            tvProgress.setText("Soal " + (currentQuestionIndex + 1) + " / " + questionList.size());

            progressQuiz.setMax(questionList.size());
            progressQuiz.setProgress(currentQuestionIndex + 1);

        } else {

            showScore();

        }

    }

    private void checkAnswer(int selectedIndex) {

        Question question = questionList.get(currentQuestionIndex);

        boolean isCorrect =
                selectedIndex == question.getCorrectAnswerIndex();

        if (isCorrect) {
            score++;
        }

        showAnswerDialog(
                isCorrect,
                question.getOptions()[question.getCorrectAnswerIndex()]
        );

    }

    private void showScore() {

        // Simpan hasil kuis ke SharedPreferences
        ProgressManager.saveQuizResult(
                this,
                score,
                questionList.size()
        );

        Intent intent = new Intent(KuisActivity.this, HasilKuisActivity.class);

        intent.putExtra("score", score);
        intent.putExtra("total", questionList.size());

        startActivity(intent);

        finish();

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

    private void showAnswerDialog(boolean isCorrect, String correctAnswer) {

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_answer);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(Color.TRANSPARENT)
            );
        }

        ImageView imgCharacter = dialog.findViewById(R.id.imgCharacter);
        TextView tvStatus = dialog.findViewById(R.id.tvStatus);
        TextView tvMessage = dialog.findViewById(R.id.tvMessage);
        TextView tvCorrectAnswer = dialog.findViewById(R.id.tvCorrectAnswer);
        Button btnNext = dialog.findViewById(R.id.btnNext);

        if (isCorrect) {

            imgCharacter.setImageResource(R.drawable.anak_kecil);

            tvStatus.setText("YEAY!");
            tvStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark));

            tvMessage.setText("Jawaban Kamu Benar 🎉");

        } else {

            imgCharacter.setImageResource(R.drawable.anak_kecil);

            tvStatus.setText("Yah...");
            tvStatus.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));

            tvMessage.setText("Jawaban Kamu Salah\nTetap Semangat Belajar 😊");

        }

        tvCorrectAnswer.setText(correctAnswer);

        btnNext.setOnClickListener(v -> {

            dialog.dismiss();

            currentQuestionIndex++;

            showQuestion();

        });

        dialog.setCancelable(false);
        dialog.show();
    }
}
