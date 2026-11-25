package com.example.evofit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class personalizacion extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personalizacion);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        RadioButton rbOscuro = findViewById(R.id.rbModoOscuro);
        RadioButton rbDegradado = findViewById(R.id.rbModoDegradado);
        Button btnGuardarTema = findViewById(R.id.btnGuardarTema);

        SharedPreferences prefs = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);
        String temaActual = prefs.getString("TEMA_ACTUAL", "OSCURO");

        if ("DEGRADADO".equals(temaActual) && rbDegradado != null) {
            rbDegradado.setChecked(true);
        } else if (rbOscuro != null) {
            rbOscuro.setChecked(true);
        }

        if (btnGuardarTema != null) {
            btnGuardarTema.setOnClickListener(v -> {
                String seleccionado = "OSCURO";
                 if (rbDegradado != null && rbDegradado.isChecked()) {
                    seleccionado = "DEGRADADO";
                }

                prefs.edit().putString("TEMA_ACTUAL", seleccionado).apply();
                Toast.makeText(this, "Tema aplicado", Toast.LENGTH_SHORT).show();
                recreate();
            });
        }
    }
}