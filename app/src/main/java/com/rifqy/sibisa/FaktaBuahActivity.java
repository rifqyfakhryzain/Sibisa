package com.rifqy.sibisa;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FaktaBuahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fakta_buah);

        ImageView btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> onBackPressed());
        }

        // Inisialisasi item buah (bisa ditambahkan listener ke detail nantinya)
        TextView itemApel = findViewById(R.id.itemApel);
        TextView itemJeruk = findViewById(R.id.itemJeruk);
        TextView itemPisang = findViewById(R.id.itemPisang);
        TextView itemMangga = findViewById(R.id.itemMangga);
        TextView itemSalak = findViewById(R.id.itemSalak);

        // Contoh: Set click listener jika diperlukan
        /*
        itemApel.setOnClickListener(v -> openDetail("Apel"));
        itemJeruk.setOnClickListener(v -> openDetail("Jeruk"));
        itemPisang.setOnClickListener(v -> openDetail("Pisang"));
        itemMangga.setOnClickListener(v -> openDetail("Mangga"));
        itemSalak.setOnClickListener(v -> openDetail("Salak"));
        */
    }
}
