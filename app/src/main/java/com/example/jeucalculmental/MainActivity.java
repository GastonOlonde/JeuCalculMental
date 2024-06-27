package com.example.jeucalculmental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Démarrer la musique
        MusicManager.startMusic(this);

        Button btnStartGame = findViewById(R.id.btnStartGame);
        Button btnViewHighScore = findViewById(R.id.btnHighScore);
        Button btnAbout = findViewById(R.id.btnAbout);
        Button btnMusicSettings = findViewById(R.id.btnMusicSettings);

        btnStartGame.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });

        btnViewHighScore.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        btnMusicSettings.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MusicSettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Arrêter la musique lorsque l'activité est détruite
        MusicManager.stopMusic();
    }
}
