package com.rifqy.sibisa;

import android.content.Intent;
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
        TextView itemPir = findViewById(R.id.itemPir);
        TextView itemNanas = findViewById(R.id.itemNanas);
        TextView itemLeci = findViewById(R.id.itemLeci);
        TextView itemAnggur = findViewById(R.id.itemAnggur);
        TextView itemSemangka = findViewById(R.id.itemSemangka);

        // Set click listener untuk item Apel
        if (itemApel != null) {
            itemApel.setOnClickListener(v -> {
                Intent intent = new Intent(FaktaBuahActivity.this, DetailApelActivity.class);
                startActivity(intent);
            });
        }

        if (itemJeruk != null) {
            itemJeruk.setOnClickListener(v -> {
                Intent intent = new Intent(FaktaBuahActivity.this, DetailJerukActivity.class);
                startActivity(intent);
            });
        }

        if (itemPisang != null) {
            itemPisang.setOnClickListener(v -> {
                Intent intent = new Intent(FaktaBuahActivity.this, DetailPisangActivity.class);
                startActivity(intent);
            });
        }

        if (itemMangga != null) {
            itemMangga.setOnClickListener(v -> {
                Intent intent = new Intent(FaktaBuahActivity.this, DetailManggaActivity.class);
                startActivity(intent);
            });
        }

        if (itemSalak != null) {
            itemSalak.setOnClickListener(v -> {
                Intent intent = new Intent(FaktaBuahActivity.this, DetailSalakActivity.class);
                startActivity(intent);
            });
        }

        if (itemPir != null) {
            itemPir.setOnClickListener(v -> {
                Intent intent = new Intent(FaktaBuahActivity.this, DetailPirActivity.class);
                startActivity(intent);
            });
        }

        if (itemNanas != null) {
            itemNanas.setOnClickListener(v -> {
                Intent intent = new Intent(FaktaBuahActivity.this, DetailNanasActivity.class);
                startActivity(intent);
            });
        }

        if (itemLeci != null) {
            itemLeci.setOnClickListener(v -> {
                Intent intent = new Intent(FaktaBuahActivity.this, DetailLeciActivity.class);
                startActivity(intent);
            });
        }

        if (itemAnggur != null) {
            itemAnggur.setOnClickListener(v -> {
                Intent intent = new Intent(FaktaBuahActivity.this, DetailAnggurActivity.class);
                startActivity(intent);
            });
        }

        if (itemSemangka != null) {
            itemSemangka.setOnClickListener(v -> {
                Intent intent = new Intent(FaktaBuahActivity.this, DetailSemangkaActivity.class);
                startActivity(intent);
            });
        }
    }
}
