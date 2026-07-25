package com.rifqy.sibisa;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBelajarActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvStatusApel;
    private TextView tvStatusJeruk;
    private TextView tvStatusPisang;
    private TextView tvStatusMangga;
    private TextView tvStatusSalak;

    private TextView tvBestScore;
    private TextView tvLastScore;
    private TextView tvProgress;
    private TextView tvBadge;

    private ProgressBar progressBelajar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_belajar);

        initViews();
        setupListeners();
        loadData();
    }


//Inisialisasi seluruh komponen UI

    private void initViews() {

        btnBack = findViewById(R.id.btnBack);

        tvStatusApel = findViewById(R.id.tvStatusApel);
        tvStatusJeruk = findViewById(R.id.tvStatusJeruk);
        tvStatusPisang = findViewById(R.id.tvStatusPisang);
        tvStatusMangga = findViewById(R.id.tvStatusMangga);
        tvStatusSalak = findViewById(R.id.tvStatusSalak);

        tvBestScore = findViewById(R.id.tvBestScore);
        tvLastScore = findViewById(R.id.tvLastScore);

        tvProgress = findViewById(R.id.tvProgress);
        tvBadge = findViewById(R.id.tvBadge);

        progressBelajar = findViewById(R.id.progressBelajar);
    }


//     * Event listener

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());
    }

    private void loadData() {

        // Status materi
        tvStatusApel.setText(
                ProgressManager.isMateriSelesai(this, "apel") ? "✅" : "❌");

        tvStatusJeruk.setText(
                ProgressManager.isMateriSelesai(this, "jeruk") ? "✅" : "❌");

        tvStatusPisang.setText(
                ProgressManager.isMateriSelesai(this, "pisang") ? "✅" : "❌");

        tvStatusMangga.setText(
                ProgressManager.isMateriSelesai(this, "mangga") ? "✅" : "❌");

        tvStatusSalak.setText(
                ProgressManager.isMateriSelesai(this, "salak") ? "✅" : "❌");

        // Nilai
        int best = ProgressManager.getBestQuizScore(this);
        int last = ProgressManager.getLastQuizScore(this);
        int total = ProgressManager.getQuizTotal(this);

        tvBestScore.setText(best + " / " + total);
        tvLastScore.setText(last + " / " + total);

        // Progress
        int progress = ProgressManager.getProgressPercentage(this);

        progressBelajar.setProgress(progress);

        tvProgress.setText(progress + "%");

        // Badge
        tvBadge.setText(
                ProgressManager.getBadge(this)
        );
    }
}