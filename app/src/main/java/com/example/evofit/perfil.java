package com.example.evofit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class perfil extends BaseActivity {

    private TextView tvUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tvUserEmail = findViewById(R.id.tvUserEmail);

        // Recibe el correo o usuario enviado
        String email = getIntent().getStringExtra("USER_EMAIL");
        if (email != null && !email.isEmpty()) {
            tvUserEmail.setText(email);
        } else {
            tvUserEmail.setText("Atleta EvoFit");
        }
    }
}