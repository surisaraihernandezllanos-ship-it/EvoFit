package com.example.evofit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class principal extends AppCompatActivity {

    private Button btnIrRutinas, btnIrEjercicios;
    private TextView btnMenu;
    private ImageView imgPerfil;
    private EditText etSearch;
    private LinearLayout layoutMain;
    private String usuarioActual = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal2);

        if (getIntent().hasExtra("USER_EMAIL")) {
            usuarioActual = getIntent().getStringExtra("USER_EMAIL");
        }

        layoutMain = findViewById(R.id.main);
        btnIrRutinas = findViewById(R.id.btnIrRutinas);
        btnIrEjercicios = findViewById(R.id.btnIrEjercicios);
        btnMenu = findViewById(R.id.btnMenu);
        imgPerfil = findViewById(R.id.imgPerfil);
        etSearch = findViewById(R.id.etSearch);

        btnIrRutinas.setOnClickListener(v -> {
            startActivity(new Intent(principal.this, rutinas.class));
        });

        btnIrEjercicios.setOnClickListener(v -> {
            startActivity(new Intent(principal.this, ejercicios.class));
        });

        // Menú desplegable de las 3 rayas (☰)
        btnMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(principal.this, btnMenu);
            popupMenu.getMenuInflater().inflate(R.menu.menu_opciones, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();

                if (id == R.id.nav_ajustes) {
                    startActivity(new Intent(principal.this, ajustes.class));
                    return true;
                } else if (id == R.id.nav_personalizar) {
                    startActivity(new Intent(principal.this, personalizacion.class));
                    return true;
                } else if (id == R.id.nav_salir) {
                    Intent intent = new Intent(principal.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            });
            popupMenu.show();
        });

        imgPerfil.setOnClickListener(v -> abrirPerfil());
    }

    @Override
    protected void onResume() {
        super.onResume();
        aplicarTemaGlobal();
    }

    private void aplicarTemaGlobal() {
        SharedPreferences preferences = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);
        String tema = preferences.getString("TEMA_ACTUAL", "OSCURO");

        if (layoutMain != null) {
            if (tema.equals("CLARO")) {
                layoutMain.setBackgroundColor(Color.parseColor("#F5F7FA"));
            } else if (tema.equals("DEGRADADO")) {
                layoutMain.setBackgroundResource(R.drawable.degradado);
            } else { // OSCURO CYBER
                layoutMain.setBackgroundColor(Color.parseColor("#0B0E14"));
            }
        }
    }

    private void abrirPerfil() {
        Intent intent = new Intent(principal.this, perfil.class);
        intent.putExtra("USER_EMAIL", usuarioActual);
        startActivity(intent);
    }
}
