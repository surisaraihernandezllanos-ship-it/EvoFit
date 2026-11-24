package com.example.evofit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class perfil extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        EditText etNombre = findViewById(R.id.etNombreUsuario);
        EditText etEstado = findViewById(R.id.etEstadoCuenta);
        Button btnGuardar = findViewById(R.id.btnGuardarPerfil);

        SharedPreferences prefs = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);

        if (etNombre != null) {
            etNombre.setText(prefs.getString("USUARIO_NOMBRE", "Atleta EvoFit"));
        }
        if (etEstado != null) {
            etEstado.setText(prefs.getString("USUARIO_ESTADO", "Activo / Entrenando"));
        }

        if (btnGuardar != null) {
            btnGuardar.setOnClickListener(v -> {
                String nombre = etNombre != null ? etNombre.getText().toString().trim() : "";
                String estado = etEstado != null ? etEstado.getText().toString().trim() : "";

                if (nombre.isEmpty()) {
                    if (etNombre != null) etNombre.setError("Ingresa un nombre o correo");
                    return;
                }

                prefs.edit()
                        .putString("USUARIO_NOMBRE", nombre)
                        .putString("USUARIO_ESTADO", estado.isEmpty() ? "Activo / Entrenando" : estado)
                        .apply();

                Toast.makeText(this, "Datos de perfil guardados", Toast.LENGTH_SHORT).show();
            });
        }
    }
}