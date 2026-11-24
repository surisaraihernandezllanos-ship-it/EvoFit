package com.example.evofit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class principal extends BaseActivity {

    private TextView tvRutinasCompletadas;
    private TextView tvDiasSemanasProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal2);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        TextView tvSaludo = findViewById(R.id.tvSaludo);
        tvRutinasCompletadas = findViewById(R.id.tvRutinasCompletadas);
        tvDiasSemanasProgreso = findViewById(R.id.tvDiasSemanasProgreso);

        Button btnRutinas = findViewById(R.id.btnRutinas);
        Button btnEjercicios = findViewById(R.id.btnEjercicios);
        ImageView btnMenu = findViewById(R.id.btnMenu);
        ImageView btnPerfil = findViewById(R.id.btnPerfil);

        String correoUsuario = getIntent().getStringExtra("CORREO_USUARIO");
        SharedPreferences prefs = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);
        String nombreGuardado = prefs.getString("USUARIO_NOMBRE", "");

        if (tvSaludo != null) {
            if (!nombreGuardado.isEmpty()) {
                tvSaludo.setText("Hola, " + nombreGuardado + " 🔥");
            } else if (correoUsuario != null && !correoUsuario.isEmpty()) {
                tvSaludo.setText("Hola, " + correoUsuario + " 🔥");
            }
        }

        if (btnRutinas != null) {
            btnRutinas.setOnClickListener(v ->
                    startActivity(new Intent(principal.this, rutinas.class)));
        }

        if (btnEjercicios != null) {
            btnEjercicios.setOnClickListener(v ->
                    startActivity(new Intent(principal.this, ejercicios.class)));
        }

        if (btnPerfil != null) {
            btnPerfil.setOnClickListener(v ->
                    startActivity(new Intent(principal.this, perfil.class)));
        }

        if (btnMenu != null) {
            btnMenu.setOnClickListener(this::mostrarPopupMenu);
        }

        actualizarResumenProgreso();
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarResumenProgreso();
    }

    private void mostrarPopupMenu(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenu().add("Mi Perfil");
        popup.getMenu().add("Ajustes");
        popup.getMenu().add("Personalización de Tema");

        popup.setOnMenuItemClickListener((MenuItem item) -> {
            String titulo = item.getTitle().toString();
            if ("Mi Perfil".equals(titulo)) {
                startActivity(new Intent(principal.this, perfil.class));
            } else if ("Ajustes".equals(titulo)) {
                startActivity(new Intent(principal.this, ajustes.class));
            } else if ("Personalización de Tema".equals(titulo)) {
                startActivity(new Intent(principal.this, personalizacion.class));
            }
            return true;
        });
        popup.show();
    }

    private void actualizarResumenProgreso() {
        if (tvRutinasCompletadas == null || tvDiasSemanasProgreso == null) return;

        SharedPreferences prefs = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);
        int rutinasRealizadas = prefs.getInt("RUTINAS_REALIZADAS", 0);
        int diasCompletados = prefs.getInt("DIAS_COMPLETADOS", 0);
        int semanasCompletadas = diasCompletados / 7;

        tvRutinasCompletadas.setText("Rutinas realizadas: " + rutinasRealizadas);
        tvDiasSemanasProgreso.setText("Días completados: " + diasCompletados + " | Semanas: " + semanasCompletadas);
    }
}