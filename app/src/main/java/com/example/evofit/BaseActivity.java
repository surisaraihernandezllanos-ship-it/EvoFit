package com.example.evofit;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        aplicarTemaGuardado();
    }

    protected void aplicarTemaGuardado() {
        View rootView = findViewById(R.id.main);
        if (rootView == null) return;

        SharedPreferences prefs = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);
        String tema = prefs.getString("TEMA_ACTUAL", "OSCURO");

        switch (tema) {
            case "CLARO":
                rootView.setBackgroundColor(Color.parseColor("#F4F6FA"));
                break;
            case "DEGRADADO":
                GradientDrawable gradiente = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{
                                Color.parseColor("#0B0E14"),
                                Color.parseColor("#2A164A"),
                                Color.parseColor("#171D27")
                        }
                );
                rootView.setBackground(gradiente);
                break;
            case "OSCURO":
            default:
                rootView.setBackgroundColor(Color.parseColor("#0B0E14"));
                break;
        }
    }
}