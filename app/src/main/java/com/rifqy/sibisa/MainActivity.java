package com.rifqy.sibisa;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.unity3d.player.UnityPlayerGameActivity;

public class MainActivity extends AppCompatActivity {

    private View btnKuis, btnFaktaBuah, btnProgressBelajar;
    private ImageButton btnArCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Tombol dari XML
        btnKuis = findViewById(R.id.btnKuis);
        btnFaktaBuah = findViewById(R.id.btnFaktaBuah);
        btnProgressBelajar = findViewById(R.id.btnProgressBelajar);
        btnArCamera = findViewById(R.id.btnArCamera);

        // Aksi ketika tombol Kuis diklik
        if (btnKuis != null) {
            btnKuis.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, KuisActivity.class);
                startActivity(intent);
            });
        }

        // Aksi ketika tombol Fakta Buah diklik
        if (btnFaktaBuah != null) {
            btnFaktaBuah.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, FaktaBuahActivity.class);
                startActivity(intent);
            });
        }

        // Aksi ketika tombol 3rd Activity diklik
        if (btnProgressBelajar != null) {
            btnProgressBelajar.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProgressBelajarActivity.class);
                startActivity(intent);
            });
        }

        // Aksi ketika tombol AR diklik
        if (btnArCamera != null) {
            btnArCamera.setOnClickListener(v -> {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            100
                    );

                } else {

                    startUnityAR();

                }
            });
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ImageButton btnMenu = findViewById(R.id.btnMenu);
        ImageView btnCloseDrawer = findViewById(R.id.btnCloseDrawer);

        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> {
                drawerLayout.openDrawer(GravityCompat.START);
            });
        }

        if (btnCloseDrawer != null) {
            btnCloseDrawer.setOnClickListener(v -> {
                drawerLayout.closeDrawer(GravityCompat.START);
            });
        }

        // Aksi Sidebar Menu
        View menuTentang = findViewById(R.id.menu_tentang);
        View menuPengaturan = findViewById(R.id.menu_pengaturan);
        View menuKeluar = findViewById(R.id.menu_keluar);

        if (menuTentang != null) {
            menuTentang.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, TentangActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            });
        }

        if (menuPengaturan != null) {
            menuPengaturan.setOnClickListener(v -> {
                // Handle Pengaturan click
                drawerLayout.closeDrawer(GravityCompat.START);
            });
        }

        if (menuKeluar != null) {
            menuKeluar.setOnClickListener(v -> {
                finish(); // Example for logout/exit
            });
        }
    }

    private void startUnityAR() {
        Intent intent = new Intent(MainActivity.this, UnityPlayerGameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {

            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                startUnityAR();

            } else {

                Toast.makeText(
                        this,
                        "Izin kamera diperlukan untuk fitur AR",
                        Toast.LENGTH_SHORT
                ).show();

            }
        }
    }
}
