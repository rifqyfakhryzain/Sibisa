package com.rifqy.sibisa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HasilKuisActivity extends AppCompatActivity {

    private TextView tvScore, tvStars, tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_kuis);

        tvScore = findViewById(R.id.tvScore);
        tvStars = findViewById(R.id.tvStars);
        tvMessage = findViewById(R.id.tvMessage);

        Button btnRetry = findViewById(R.id.btnRetry);
        Button btnHome = findViewById(R.id.btnHome);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 5);

        tvScore.setText(score + " / " + total);

        String stars = "";

        switch (score) {

            case 5:
                stars = "⭐⭐⭐⭐⭐";
                break;

            case 4:
                stars = "⭐⭐⭐⭐☆";
                break;

            case 3:
                stars = "⭐⭐⭐☆☆";
                break;

            case 2:
                stars = "⭐⭐☆☆☆";
                break;

            case 1:
                stars = "⭐☆☆☆☆";
                break;

            default:
                stars = "☆☆☆☆☆";
                break;
        }

        tvStars.setText(stars);

        if (score >= 4) {

            tvMessage.setText("Hebat! Kamu sudah memahami materi dengan sangat baik.");

        } else if (score >= 2) {

            tvMessage.setText("Bagus! Terus belajar agar hasilnya lebih baik.");

        } else {

            tvMessage.setText("Ayo semangat! Coba ulangi kuis lagi ya 😊");

        }

        btnRetry.setOnClickListener(v -> {

            startActivity(new Intent(this, KuisActivity.class));
            finish();

        });

        btnHome.setOnClickListener(v -> {

            startActivity(new Intent(this, MainActivity.class));
            finish();

        });

    }
}