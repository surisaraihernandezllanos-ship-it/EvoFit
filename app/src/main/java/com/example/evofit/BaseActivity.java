package com.example.evofit;

import android.content.SharedPreferences;
import android.graphics.Color;
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
        aplicarTemaGlobalEnPantalla();
    }

    protected void aplicarTemaGlobalEnPantalla() {
        SharedPreferences preferences = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);
        String tema = preferences.getString("TEMA_ACTUAL", "OSCURO");

        // Busca el contenedor raíz de la pantalla actual (asegúrate de que tus XML principales tengan android:id="@+id/main" o el id que uses)
        View rootView = findViewById(R.id.main);

        if (rootView != null) {
            if (tema.equals("CLARO")) {
                rootView.setBackgroundColor(Color.parseColor("#F5F7FA"));
            } else if (tema.equals("DEGRADADO")) {
                rootView.setBackgroundResource(R.drawable.degradado);
            } else { // OSCURO CYBER (Por defecto)
                rootView.setBackgroundColor(Color.parseColor("#0B0E14"));
            }
        }
    }
}