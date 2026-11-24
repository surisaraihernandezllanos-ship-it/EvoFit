package com.example.evofit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class rutinas extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutinas);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        Button btnMarcar1 = findViewById(R.id.btnMarcar1);
        Button btnMarcar2 = findViewById(R.id.btnMarcar2);
        Button btnFinalizar = findViewById(R.id.btnFinalizarEntrenamiento);

        if (btnMarcar1 != null) {
            btnMarcar1.setOnClickListener(v -> {
                btnMarcar1.setText("¡Completado! 🔥");
                btnMarcar1.setEnabled(false);
            });
        }

        if (btnMarcar2 != null) {
            btnMarcar2.setOnClickListener(v -> {
                btnMarcar2.setText("¡Completado! 🔥");
                btnMarcar2.setEnabled(false);
            });
        }

        if (btnFinalizar != null) {
            btnFinalizar.setOnClickListener(v -> {
                SharedPreferences prefs = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);
                int rutinasActuales = prefs.getInt("RUTINAS_REALIZADAS", 0);
                int diasActuales = prefs.getInt("DIAS_COMPLETADOS", 0);

                prefs.edit()
                        .putInt("RUTINAS_REALIZADAS", rutinasActuales + 1)
                        .putInt("DIAS_COMPLETADOS", diasActuales + 1)
                        .apply();

                Toast.makeText(this, "¡Entrenamiento guardado con éxito! 💪", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }
}