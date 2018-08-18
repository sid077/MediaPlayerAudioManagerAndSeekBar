package com.siddhant.sounddemo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer= MediaPlayer.create(this,R.raw.sound);
        audioManager= (AudioManager) getSystemService(this.AUDIO_SERVICE);
        int maxVol=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVol =audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(maxVol);
        seekBar.setProgress(currVol);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("SeekBar value",Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final SeekBar soundProgress = findViewById(R.id.seekBar2);
        int maxvalue = mediaPlayer.getDuration();
        soundProgress.setMax(maxvalue);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                soundProgress.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,100);


        soundProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



    public void playAudio(View view) {
        mediaPlayer.start();

    }

    public void pauseAudio(View view) {
        mediaPlayer.pause();
    }
}
