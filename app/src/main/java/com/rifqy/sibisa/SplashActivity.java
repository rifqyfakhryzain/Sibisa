package com.rifqy.sibisa;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 3000;

    private View dot1, dot2, dot3;

    private final Handler dotHandler = new Handler(Looper.getMainLooper());

    private final Runnable dotAnimation = new Runnable() {
        @Override
        public void run() {

            animateDot(dot1, 0);
            animateDot(dot2, 180);
            animateDot(dot3, 360);

            dotHandler.postDelayed(this, 700);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // ============================
        // Inisialisasi View
        // ============================

        ImageView logo = findViewById(R.id.imgLogo);

        TextView title = findViewById(R.id.tvTitle);

        TextView subtitle = findViewById(R.id.tvSubtitle);

        TextView tagline = findViewById(R.id.tvTagline);

        dot1 = findViewById(R.id.dot1);
        dot2 = findViewById(R.id.dot2);
        dot3 = findViewById(R.id.dot3);

        // ============================
        // Animasi Logo
        // ============================

        logo.setScaleX(0.6f);
        logo.setScaleY(0.6f);

        AnimatorSet logoAnimation = new AnimatorSet();

        logoAnimation.playTogether(
                ObjectAnimator.ofFloat(logo, "alpha", 0f, 1f),
                ObjectAnimator.ofFloat(logo, "scaleX", 0.6f, 1f),
                ObjectAnimator.ofFloat(logo, "scaleY", 0.6f, 1f)
        );

        logoAnimation.setDuration(700);
        logoAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        logoAnimation.start();

        // ============================
        // Floating Logo
        // ============================

        ObjectAnimator floating =
                ObjectAnimator.ofFloat(
                        logo,
                        "translationY",
                        0f,
                        -10f,
                        0f
                );

        floating.setDuration(1800);
        floating.setRepeatCount(ObjectAnimator.INFINITE);
        floating.start();

        // ============================
        // Fade In Text
        // ============================

        title.animate()
                .alpha(1f)
                .setStartDelay(500)
                .setDuration(500);

        subtitle.animate()
                .alpha(1f)
                .setStartDelay(800)
                .setDuration(500);

        tagline.animate()
                .alpha(1f)
                .setStartDelay(1100)
                .setDuration(500);

        // ============================
        // Start Loading Animation
        // ============================

        dotHandler.post(dotAnimation);

        // ============================
        // Pindah ke MainActivity
        // ============================

        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            dotHandler.removeCallbacks(dotAnimation);

            startActivity(new Intent(SplashActivity.this, MainActivity.class));

            finish();

        }, SPLASH_TIME);
    }

    // ===========================================
    // Animasi Titik Loading
    // ===========================================

    private void animateDot(View dot, long delay) {

        ObjectAnimator scaleX =
                ObjectAnimator.ofFloat(dot, "scaleX", 1f, 1.7f, 1f);

        ObjectAnimator scaleY =
                ObjectAnimator.ofFloat(dot, "scaleY", 1f, 1.7f, 1f);

        ObjectAnimator alpha =
                ObjectAnimator.ofFloat(dot, "alpha", 0.4f, 1f, 0.4f);

        AnimatorSet set = new AnimatorSet();

        set.playTogether(scaleX, scaleY, alpha);

        set.setDuration(450);

        set.setStartDelay(delay);

        set.start();
    }
}