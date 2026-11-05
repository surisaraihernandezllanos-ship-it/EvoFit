package com.example.evofit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ejercicios extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        LinearLayout cardEjercicio1 = findViewById(R.id.cardEjercicio1);
        LinearLayout cardEjercicio2 = findViewById(R.id.cardEjercicio2);
        LinearLayout cardEjercicio3 = findViewById(R.id.cardEjercicio3);
        Button btnVolverPrincipal = findViewById(R.id.btnVolverPrincipal);

        // Interacciones al hacer clic en cada ejercicio de la lista
        if (cardEjercicio1 != null) {
            cardEjercicio1.setOnClickListener(v ->
                    Toast.makeText(this, "Mostrando detalles de: Sentadilla Libre", Toast.LENGTH_SHORT).show()
            );
        }

        if (cardEjercicio2 != null) {
            cardEjercicio2.setOnClickListener(v ->
                    Toast.makeText(this, "Mostrando detalles de: Press Militar", Toast.LENGTH_SHORT).show()
            );
        }

        if (cardEjercicio3 != null) {
            cardEjercicio3.setOnClickListener(v ->
                    Toast.makeText(this, "Mostrando detalles de: Peso Muerto Rumano", Toast.LENGTH_SHORT).show()
            );
        }

        // Botón para regresar
        if (btnVolverPrincipal != null) {
            btnVolverPrincipal.setOnClickListener(v -> finish());
        }
    }
}
