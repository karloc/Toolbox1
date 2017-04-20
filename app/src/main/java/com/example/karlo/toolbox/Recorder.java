package com.example.karlo.toolbox;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class Recorder extends AppCompatActivity implements View.OnClickListener{

    final Context context = this;
    MediaRecorder recorder;
    File audiofile = null;
    TextView Ctd;
    private Button btnStart;
    private Button btnStop;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setLogo(R.mipmap.toolsab);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        Ctd=(TextView)findViewById(R.id.Ctd);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this); }


    public void onClick(View v){

        if(v.getId() == R.id.btnStart) { startRecording();
            Toast.makeText(getApplicationContext(), "Snimanje započelo", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.btnStop) {
        stopRecording();
            Toast.makeText(getApplicationContext(), "Snimanje završeno", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Greška u snimanju", Toast.LENGTH_SHORT).show();
        }

    } private void startRecording(){
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
        File sampleDir = Environment.getExternalStorageDirectory();
        try{

    audiofile = File.createTempFile("zvuk", ".3gp", sampleDir); }
        catch (IOException e){
            return; } recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(audiofile.getAbsolutePath()); try{
            recorder.prepare(); }catch (IllegalStateException e){ e.printStackTrace();}
        catch (IOException e){ e.printStackTrace(); }
        recorder.start(); }



    private void stopRecording(){
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        recorder.stop();
        recorder.release(); addRecordingToMediaLibrary(); }

    private void addRecordingToMediaLibrary(){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(audiofile)); sendBroadcast(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int sett = item.getItemId();
        if (sett == R.id.Settings) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", getPackageName(), null));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        int ex = item.getItemId();
        if (ex == R.id.Exit) {


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set title
            alertDialogBuilder.setTitle("Izlaz!");


            // set dialog message
            alertDialogBuilder
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage("Jeste li sigurni da želite izaći?")
                    .setCancelable(false)
                    .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
        return true;
    }
}