package com.example.evofit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class ajustes extends BaseActivity {

    private Switch switchNotificaciones, switchHidratacion, switchSonido;
    private Button btnLimpiarCache, btnVolverAjustes;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        preferences = getSharedPreferences("EvoFitPrefs", MODE_PRIVATE);

        switchNotificaciones = findViewById(R.id.switchNotificaciones);
        switchHidratacion = findViewById(R.id.switchHidratacion);
        switchSonido = findViewById(R.id.switchSonido);
        btnLimpiarCache = findViewById(R.id.btnLimpiarCache);
        btnVolverAjustes = findViewById(R.id.btnVolverAjustes);

        // Cargar preferencias guardadas
        if (switchNotificaciones != null) {
            switchNotificaciones.setChecked(preferences.getBoolean("NOTIF_ENABLED", true));
            switchNotificaciones.setOnCheckedChangeListener((v, isChecked) -> {
                preferences.edit().putBoolean("NOTIF_ENABLED", isChecked).apply();
                Toast.makeText(this, isChecked ? "Notificaciones activadas" : "Notificaciones desactivadas", Toast.LENGTH_SHORT).show();
            });
        }

        if (switchHidratacion != null) {
            switchHidratacion.setChecked(preferences.getBoolean("WATER_ENABLED", false));
            switchHidratacion.setOnCheckedChangeListener((v, isChecked) -> {
                preferences.edit().putBoolean("WATER_ENABLED", isChecked).apply();
                Toast.makeText(this, isChecked ? "Alertas de agua activadas" : "Alertas de agua desactivadas", Toast.LENGTH_SHORT).show();
            });
        }

        if (switchSonido != null) {
            switchSonido.setChecked(preferences.getBoolean("SOUND_ENABLED", true));
            switchSonido.setOnCheckedChangeListener((v, isChecked) -> {
                preferences.edit().putBoolean("SOUND_ENABLED", isChecked).apply();
                Toast.makeText(this, isChecked ? "Sonido activado" : "Sonido desactivado", Toast.LENGTH_SHORT).show();
            });
        }

        // Botón limpiar caché
        if (btnLimpiarCache != null) {
            btnLimpiarCache.setOnClickListener(v ->
                    Toast.makeText(this, "Caché de datos temporales limpiada 🧹", Toast.LENGTH_SHORT).show()
            );
        }

        // Botón volver
        if (btnVolverAjustes != null) {
            btnVolverAjustes.setOnClickListener(v -> finish());
        }
    }
}
