package com.example.evofit;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class perfil extends BaseActivity {

    private static final String PREFS_NAME = "EvoFitPrefs";
    private static final String NOMBRE_ARCHIVO_FOTO = "perfil_usuario.jpg";

    private ImageView imgAvatarPerfil;
    private EditText etNombreUsuario;
    private EditText etEdad;
    private EditText etPeso;
    private EditText etAltura;
    private EditText etNivelFisico;
    private EditText etEstadoCuenta;

    private final ActivityResultLauncher<String> selectorGaleria =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    guardarYMostrarFoto(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        imgAvatarPerfil = findViewById(R.id.imgAvatarPerfil);
        Button btnCambiarFoto = findViewById(R.id.btnCambiarFoto);
        etNombreUsuario = findViewById(R.id.etNombreUsuario);
        etEdad = findViewById(R.id.etEdad);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        etNivelFisico = findViewById(R.id.etNivelFisico);
        etEstadoCuenta = findViewById(R.id.etEstadoCuenta);
        Button btnGuardar = findViewById(R.id.btnGuardarPerfil);
        Button btnVolver = findViewById(R.id.btnVolverPerfil);

        cargarDatosGuardados();
        cargarFotoGuardada();

        if (imgAvatarPerfil != null) {
            imgAvatarPerfil.setOnClickListener(v -> selectorGaleria.launch("image/*"));
        }

        if (btnCambiarFoto != null) {
            btnCambiarFoto.setOnClickListener(v -> selectorGaleria.launch("image/*"));
        }

        if (btnGuardar != null) {
            btnGuardar.setOnClickListener(v -> guardarDatosPerfil());
        }

        if (btnVolver != null) {
            btnVolver.setOnClickListener(v -> finish());
        }
    }

    private void guardarYMostrarFoto(Uri uri) {
        try (InputStream inputStream = getContentResolver().openInputStream(uri)) {
            if (inputStream == null) return;
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            if (bitmap == null) return;

            File archivoDestino = new File(getFilesDir(), NOMBRE_ARCHIVO_FOTO);
            try (FileOutputStream out = new FileOutputStream(archivoDestino)) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            }

            if (imgAvatarPerfil != null) {
                imgAvatarPerfil.setImageBitmap(bitmap);
            }
            Toast.makeText(this, "Foto de perfil actualizada", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "No se pudo cargar la imagen", Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarFotoGuardada() {
        if (imgAvatarPerfil == null) return;
        File archivoFoto = new File(getFilesDir(), NOMBRE_ARCHIVO_FOTO);
        if (archivoFoto.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(archivoFoto.getAbsolutePath());
            if (bitmap != null) {
                imgAvatarPerfil.setImageBitmap(bitmap);
            }
        }
    }

    private void cargarDatosGuardados() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        if (etNombreUsuario != null) {
            etNombreUsuario.setText(prefs.getString("USUARIO_NOMBRE", "Atleta EvoFit"));
        }
        if (etEdad != null) {
            etEdad.setText(prefs.getString("USUARIO_EDAD", "20"));
        }
        if (etPeso != null) {
            etPeso.setText(prefs.getString("USUARIO_PESO", "70.0"));
        }
        if (etAltura != null) {
            etAltura.setText(prefs.getString("USUARIO_ALTURA", "175"));
        }
        if (etNivelFisico != null) {
            etNivelFisico.setText(prefs.getString("USUARIO_NIVEL", "Intermedio"));
        }
        if (etEstadoCuenta != null) {
            etEstadoCuenta.setText(prefs.getString("USUARIO_ESTADO", "Activo / Entrenando"));
        }
    }

    private void guardarDatosPerfil() {
        String nombre = etNombreUsuario != null ? etNombreUsuario.getText().toString().trim() : "";
        String edad = etEdad != null ? etEdad.getText().toString().trim() : "";
        String peso = etPeso != null ? etPeso.getText().toString().trim() : "";
        String altura = etAltura != null ? etAltura.getText().toString().trim() : "";
        String nivel = etNivelFisico != null ? etNivelFisico.getText().toString().trim() : "";
        String estado = etEstadoCuenta != null ? etEstadoCuenta.getText().toString().trim() : "";

        if (nombre.isEmpty()) {
            if (etNombreUsuario != null) etNombreUsuario.setError("Ingresa tu nombre o correo");
            return;
        }

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit()
                .putString("USUARIO_NOMBRE", nombre)
                .putString("USUARIO_EDAD", edad.isEmpty() ? "20" : edad)
                .putString("USUARIO_PESO", peso.isEmpty() ? "70.0" : peso)
                .putString("USUARIO_ALTURA", altura.isEmpty() ? "175" : altura)
                .putString("USUARIO_NIVEL", nivel.isEmpty() ? "Intermedio" : nivel)
                .putString("USUARIO_ESTADO", estado.isEmpty() ? "Activo / Entrenando" : estado)
                .apply();

        Toast.makeText(this, "Datos de perfil guardados correctamente", Toast.LENGTH_SHORT).show();
    }
}