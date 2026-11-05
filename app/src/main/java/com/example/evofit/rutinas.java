package com.example.evofit;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class rutinas extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas);

        Button btnCompletar1 = findViewById(R.id.btnCompletar1);
        Button btnCompletar2 = findViewById(R.id.btnCompletar2);
        Button btnTerminarRutina = findViewById(R.id.btnTerminarRutina);

        // Interacción para el ejercicio 1
        if (btnCompletar1 != null) {
            btnCompletar1.setOnClickListener(v -> {
                btnCompletar1.setText("¡Completado! 🔥");
                btnCompletar1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8B949E")));
                Toast.makeText(this, "¡Buen trabajo en la serie!", Toast.LENGTH_SHORT).show();
            });
        }

        // Interacción para el ejercicio 2
        if (btnCompletar2 != null) {
            btnCompletar2.setOnClickListener(v -> {
                btnCompletar2.setText("¡Completado! 🔥");
                btnCompletar2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8B949E")));
                Toast.makeText(this, "¡Excelente esfuerzo!", Toast.LENGTH_SHORT).show();
            });
        }

        // Botón para finalizar toda la sesión
        if (btnTerminarRutina != null) {
            btnTerminarRutina.setOnClickListener(v -> {
                Toast.makeText(this, "¡Entrenamiento guardado con éxito! 💪", Toast.LENGTH_LONG).show();
                finish(); // Cierra la pantalla y regresa al dashboard principal
            });
        }
    }
}
