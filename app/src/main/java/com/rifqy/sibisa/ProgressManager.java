package com.rifqy.sibisa;

import android.content.Context;
import android.content.SharedPreferences;

public class ProgressManager {

    private static final String PREF_NAME = "progress_pref";

    // Key Quiz
    private static final String KEY_BEST_SCORE = "best_quiz_score";
    private static final String KEY_LAST_SCORE = "last_quiz_score";
    private static final String KEY_QUIZ_TOTAL = "quiz_total";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    // ==========================================================
    // MATERI
    // ==========================================================

    public static void setMateriSelesai(Context context, String namaBuah) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean("materi_" + namaBuah, true);
        editor.apply();
    }

    public static boolean isMateriSelesai(Context context, String namaBuah) {
        return getPreferences(context)
                .getBoolean("materi_" + namaBuah, false);
    }

    // ==========================================================
    // QUIZ
    // ==========================================================

    public static void saveQuizResult(Context context, int score, int total) {

        SharedPreferences preferences = getPreferences(context);

        int bestScore = preferences.getInt(KEY_BEST_SCORE, 0);

        SharedPreferences.Editor editor = preferences.edit();

        // Simpan nilai terakhir
        editor.putInt(KEY_LAST_SCORE, score);

        // Update nilai terbaik jika lebih besar
        if (score > bestScore) {
            editor.putInt(KEY_BEST_SCORE, score);
        }

        // Simpan total soal
        editor.putInt(KEY_QUIZ_TOTAL, total);

        editor.apply();
    }

    public static int getBestQuizScore(Context context) {
        return getPreferences(context)
                .getInt(KEY_BEST_SCORE, 0);
    }

    public static int getLastQuizScore(Context context) {
        return getPreferences(context)
                .getInt(KEY_LAST_SCORE, 0);
    }

    public static int getQuizTotal(Context context) {
        return getPreferences(context)
                .getInt(KEY_QUIZ_TOTAL, 5);
    }

    // ==========================================================
    // HITUNG PROGRESS
    // ==========================================================

    public static int getMateriSelesaiCount(Context context) {

        int count = 0;

        if (isMateriSelesai(context, "apel")) count++;
        if (isMateriSelesai(context, "jeruk")) count++;
        if (isMateriSelesai(context, "pisang")) count++;
        if (isMateriSelesai(context, "mangga")) count++;
        if (isMateriSelesai(context, "salak")) count++;

        return count;
    }

    public static int getProgressPercentage(Context context) {

        int materiSelesai = getMateriSelesaiCount(context);

        // Maksimal 50%
        int materiProgress = materiSelesai * 10;

        int bestScore = getBestQuizScore(context);
        int totalQuiz = getQuizTotal(context);

        int quizProgress = 0;

        if (totalQuiz > 0) {
            quizProgress = (bestScore * 50) / totalQuiz;
        }

        return materiProgress + quizProgress;
    }

    // ==========================================================
    // BADGE
    // ==========================================================

    public static String getBadge(Context context) {

        int progress = getProgressPercentage(context);

        if (progress <= 20) {
            return "🌱 Pemula";
        } else if (progress <= 40) {
            return "🌿 Pembelajar";
        } else if (progress <= 60) {
            return "🌳 Pintar";
        } else if (progress <= 80) {
            return "⭐ Hebat";
        } else {
            return "🏆 Ahli Buah";
        }
    }

    // ==========================================================
    // RESET (Opsional untuk pengembangan)
    // ==========================================================

    public static void resetProgress(Context context) {
        getEditor(context).clear().apply();
    }
}