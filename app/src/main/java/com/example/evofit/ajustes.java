package com.example.evofit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;

public class ajustes extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajustes);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        SwitchCompat swNotificaciones = findViewById(R.id.swNotificaciones);
        SwitchCompat swHidratacion = findViewById(R.id.swHidratacion);
        SwitchCompat swSonido = findViewById(R.id.swSonido);
        Button btnLimpiarCache = findViewById(R.id.btnLimpiarCache);
        Button btnVolver = findViewById(R.id.btnVolverDashboard);

        SharedPreferences prefs = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);

        if (swNotificaciones != null) {
            swNotificaciones.setChecked(prefs.getBoolean("AJUSTE_NOTIF", true));
            swNotificaciones.setOnCheckedChangeListener((btn, isChecked) ->
                    prefs.edit().putBoolean("AJUSTE_NOTIF", isChecked).apply());
        }

        if (swHidratacion != null) {
            swHidratacion.setChecked(prefs.getBoolean("AJUSTE_AGUA", false));
            swHidratacion.setOnCheckedChangeListener((btn, isChecked) ->
                    prefs.edit().putBoolean("AJUSTE_AGUA", isChecked).apply());
        }

        if (swSonido != null) {
            swSonido.setChecked(prefs.getBoolean("AJUSTE_SONIDO", true));
            swSonido.setOnCheckedChangeListener((btn, isChecked) ->
                    prefs.edit().putBoolean("AJUSTE_SONIDO", isChecked).apply());
        }

        if (btnLimpiarCache != null) {
            btnLimpiarCache.setOnClickListener(v -> {
                try {
                    File cacheDir = getCacheDir();
                    if (cacheDir != null && cacheDir.isDirectory() && cacheDir.listFiles() != null) {
                        for (File child : cacheDir.listFiles()) {
                            child.delete();
                        }
                    }
                } catch (Exception ignored) {
                }
                Toast.makeText(this, "Caché limpiada correctamente", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnVolver != null) {
            btnVolver.setOnClickListener(v -> finish());
        }
    }
}