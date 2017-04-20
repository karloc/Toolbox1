package com.example.karlo.toolbox;

/**
 * Created by Karlo on 5.2.2017..
 */

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;


public class Flashlight extends AppCompatActivity {

    Camera cam;
    Camera.Parameters camParams;
    boolean hasCam;
    int freq;
    StroboRunner sr;
    Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                turnOnOff(isChecked);
            }
        });

        SeekBar skBar = (SeekBar) findViewById(R.id.seekBar);
        skBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                freq = progress;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            cam = Camera.open();
            camParams = cam.getParameters();
            cam.startPreview();
            hasCam = true;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void turnOnOff(boolean on) {
        if(on) {

            if(freq != 0) {
                sr = new StroboRunner();
                sr.freq = freq;
                t = new Thread(sr);
                t.start();
                return;
            } else {
                camParams.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            }

        } else if(!on) {
            if(t != null) {
                sr.stopRunning = true;
                t = null;
                return;
            } else {
                camParams.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            }
        }

        cam.setParameters(camParams);
        cam.startPreview();
    }

    private class StroboRunner implements Runnable {
        int freq;
        boolean stopRunning = false;

        @Override
        public void run() {
            Camera.Parameters paramsOn = cam.getParameters();
            Camera.Parameters paramsOff = camParams;
            paramsOn.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            paramsOff.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);

            try {
                while(!stopRunning) {
                    cam.setParameters(paramsOn);
                    cam.startPreview();
                    Thread.sleep(100 - freq);
                    cam.setParameters(paramsOff);
                    cam.startPreview();
                    Thread.sleep(freq);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }


}
