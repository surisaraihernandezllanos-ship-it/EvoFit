package com.example.evofit;

import android.content.Intent;
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

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnComenzar = findViewById(R.id.btnComenzar);

        if (btnComenzar != null) {
            btnComenzar.setOnClickListener(v -> {
                String usuario = etUsuario != null ? etUsuario.getText().toString().trim() : "";
                String password = etPassword != null ? etPassword.getText().toString().trim() : "";

                if (usuario.isEmpty()) {
                    if (etUsuario != null) etUsuario.setError("Ingresa tu correo o usuario");
                    return;
                }
                if (password.isEmpty()) {
                    if (etPassword != null) etPassword.setError("Ingresa tu contraseña");
                    return;
                }

                SharedPreferences prefs = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);
                prefs.edit().putString("USUARIO_NOMBRE", usuario).apply();

                Toast.makeText(this, "Bienvenido a EvoFit", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, principal.class);
                intent.putExtra("CORREO_USUARIO", usuario);
                startActivity(intent);
            });
        }
    }
}