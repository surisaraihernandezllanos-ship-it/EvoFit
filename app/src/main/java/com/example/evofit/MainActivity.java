package com.example.evofit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends BaseActivity {

    private EditText etUser, etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Cargar preferencia guardada de modo oscuro/claro antes de inflar la vista
        SharedPreferences preferences = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);
        boolean isDarkMode = preferences.getBoolean("DARK_MODE", true);

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String usuario = etUser.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show();

                // Pasa a la principal enviando el correo/usuario
                Intent intent = new Intent(MainActivity.this, principal.class);
                intent.putExtra("USER_EMAIL", usuario);
                startActivity(intent);
                finish();
            }
        });
    }
}