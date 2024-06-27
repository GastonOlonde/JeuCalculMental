package com.example.jeucalculmental;


import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class MusicSettingsActivity extends AppCompatActivity {

    private Switch switchMusic;
    private SeekBar seekBarVolume;
    private Button btnNextMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_settings);

        switchMusic = findViewById(R.id.switchMusic);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        btnNextMusic = findViewById(R.id.btnNextMusic);


        switchMusic.setChecked(MusicManager.isPlaying());
        seekBarVolume.setMax(200);
        seekBarVolume.setProgress(MusicManager.getVolume());

        switchMusic.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                MusicManager.startMusic(getApplicationContext());
            } else {
                MusicManager.stopMusic();
            }
        });

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MusicManager.setVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnNextMusic.setOnClickListener(v -> {
            MusicManager.playNext(getApplicationContext());
        });
    }
}
