package com.example.evofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ejercicios extends BaseActivity {

    private static class EjercicioData {
        final String nombre;
        final String musculo;
        final String instrucciones;
        final int videoResId;

        EjercicioData(String nombre, String musculo, String instrucciones, int videoResId) {
            this.nombre = nombre;
            this.musculo = musculo;
            this.instrucciones = instrucciones;
            this.videoResId = videoResId;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicios);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        configurarEjercicio(
                findViewById(R.id.cardSentadilla),
                new EjercicioData(
                        "Sentadilla Libre con Barra",
                        "Piernas / Cuádriceps",
                        "1. Coloca los pies al ancho de los hombros.\n2. Mantén el torso firme y la espalda neutra.\n3. Baja controlando la cadera y sube empujando con los talones.",
                        0
                )
        );

        configurarEjercicio(
                findViewById(R.id.cardPressMilitar),
                new EjercicioData(
                        "Press Militar de Hombros",
                        "Hombros / Deltoides",
                        "1. Activa el abdomen y glúteos para estabilizar el tronco.\n2. Empuja hacia arriba sin arquear la zona lumbar.\n3. Desciende con control hasta la posición inicial.",
                        0
                )
        );

        configurarEjercicio(
                findViewById(R.id.cardPesoMuerto),
                new EjercicioData(
                        "Peso Muerto Rumano",
                        "Isquiotibiales / Espalda Baja",
                        "1. Mantén una ligera flexión de rodillas durante todo el recorrido.\n2. Lleva la cadera atrás sintiendo el estiramiento femoral.\n3. Regresa a la vertical contrayendo glúteos e isquiotibiales.",
                        0
                )
        );

        Button btnRegresar = findViewById(R.id.btnRegresarDashboard);
        if (btnRegresar != null) {
            btnRegresar.setOnClickListener(v -> finish());
        }
    }

    private void configurarEjercicio(View vista, EjercicioData data) {
        if (vista == null) return;
        vista.setOnClickListener(v -> {
            Intent intent = new Intent(ejercicios.this, detalleEjercicio.class);
            intent.putExtra("NOMBRE_EJERCICIO", data.nombre);
            intent.putExtra("GRUPO_MUSCULAR", data.musculo);
            intent.putExtra("INSTRUCCIONES", data.instrucciones);
            intent.putExtra("VIDEO_RES_ID", data.videoResId);
            startActivity(intent);
        });
    }
}
