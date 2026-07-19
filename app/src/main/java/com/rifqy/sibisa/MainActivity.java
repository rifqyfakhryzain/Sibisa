package com.rifqy.sibisa;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private View btnKuis, btnFaktaBuah, btnThirdActivity;
    private ImageButton btnArCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Tombol dari XML
        btnKuis = findViewById(R.id.btnKuis);
        btnFaktaBuah = findViewById(R.id.btnFaktaBuah);
        btnThirdActivity = findViewById(R.id.btnThirdActivity);
        btnArCamera = findViewById(R.id.btnArCamera);

        // Aksi ketika tombol Kuis diklik
        btnKuis.setOnClickListener(v -> {
            // Intent intent = new Intent(MainActivity.this, KuisActivity.class);
            // startActivity(intent);
        });

        // Aksi ketika tombol Fakta Buah diklik
        btnFaktaBuah.setOnClickListener(v -> {
            // Intent intent = new Intent(MainActivity.this, FaktaBuahActivity.class);
            // startActivity(intent);
        });

        // Aksi ketika tombol AR diklik
        btnArCamera.setOnClickListener(v -> {
            // Di sini nanti kita arahkan ke ArCameraActivity
            // Intent intent = new Intent(MainActivity.this, ArCameraActivity.class);
            // startActivity(intent);
        });
    }
}