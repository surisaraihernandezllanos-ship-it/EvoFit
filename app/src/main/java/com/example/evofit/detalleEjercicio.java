package com.example.evofit;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class detalleEjercicio extends BaseActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_ejercicio);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        TextView tvTitulo = findViewById(R.id.tvTituloEjercicio);
        TextView tvMusculo = findViewById(R.id.tvGrupoMuscular);
        TextView tvInstrucciones = findViewById(R.id.tvInstrucciones);
        videoView = findViewById(R.id.videoEjercicio);
        Button btnVolver = findViewById(R.id.btnVolverEjercicios);

        String nombre = getIntent().getStringExtra("NOMBRE_EJERCICIO");
        String musculo = getIntent().getStringExtra("GRUPO_MUSCULAR");
        String instrucciones = getIntent().getStringExtra("INSTRUCCIONES");
        int videoResId = getIntent().getIntExtra("VIDEO_RES_ID", 0);

        if (tvTitulo != null && nombre != null) tvTitulo.setText(nombre);
        if (tvMusculo != null && musculo != null) tvMusculo.setText("Grupo Muscular: " + musculo);
        if (tvInstrucciones != null && instrucciones != null) tvInstrucciones.setText(instrucciones);

        if (videoView != null && videoResId != 0) {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + videoResId);
            videoView.setVideoURI(uri);

            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            videoView.setOnPreparedListener(mp -> {
                mp.setLooping(true);
                videoView.start();
            });
        }

        if (btnVolver != null) {
            btnVolver.setOnClickListener(v -> finish());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView != null && videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videoView != null && !videoView.isPlaying()) {
            videoView.start();
        }
    }
}