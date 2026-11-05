package com.example.evofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class personalizacion extends AppCompatActivity {

    private RadioGroup rgTemas;
    private RadioButton rbOscuro, rbClaro, rbDegradado;
    private Button btnAplicarTema;
    private LinearLayout layoutPersonalizacion;
    private TextView tvTitulo;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizacion);

        preferences = getSharedPreferences("EvoFitPrefs", Context.MODE_PRIVATE);

        layoutPersonalizacion = findViewById(R.id.layoutPersonalizacion);
        tvTitulo = findViewById(R.id.tvTitulo);
        rgTemas = findViewById(R.id.rgTemas);
        rbOscuro = findViewById(R.id.rbOscuro);
        rbClaro = findViewById(R.id.rbClaro);
        rbDegradado = findViewById(R.id.rbDegradado);
        btnAplicarTema = findViewById(R.id.btnAplicarTema);

        String temaActual = preferences.getString("TEMA_ACTUAL", "OSCURO");
        if (temaActual.equals("CLARO")) {
            rbClaro.setChecked(true);
        } else if (temaActual.equals("DEGRADADO")) {
            rbDegradado.setChecked(true);
        } else {
            rbOscuro.setChecked(true);
        }

        aplicarPaletaVista(temaActual);

        btnAplicarTema.setOnClickListener(v -> {
            String nuevoTema = "OSCURO";
            int selectedId = rgTemas.getCheckedRadioButtonId();

            if (selectedId == R.id.rbClaro) {
                nuevoTema = "CLARO";
            } else if (selectedId == R.id.rbDegradado) {
                nuevoTema = "DEGRADADO";
            }

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("TEMA_ACTUAL", nuevoTema);
            editor.apply();

            Toast.makeText(this, "Tema aplicado correctamente", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, principal.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void aplicarPaletaVista(String tema) {
        if (tema.equals("CLARO")) {
            layoutPersonalizacion.setBackgroundColor(Color.parseColor("#F5F7FA"));
            tvTitulo.setTextColor(Color.parseColor("#7000FF"));
            btnAplicarTema.setBackgroundColor(Color.parseColor("#7000FF"));
            btnAplicarTema.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (tema.equals("DEGRADADO")) {
            layoutPersonalizacion.setBackgroundResource(R.drawable.degradado);
            tvTitulo.setTextColor(Color.parseColor("#00FF87"));
            btnAplicarTema.setBackgroundColor(Color.parseColor("#00FF87"));
            btnAplicarTema.setTextColor(Color.parseColor("#0B0E14"));
        } else { // OSCURO CYBER
            layoutPersonalizacion.setBackgroundColor(Color.parseColor("#0B0E14"));
            tvTitulo.setTextColor(Color.parseColor("#60EFFF"));
            btnAplicarTema.setBackgroundColor(Color.parseColor("#00FF87"));
            btnAplicarTema.setTextColor(Color.parseColor("#0B0E14"));
        }
    }
}