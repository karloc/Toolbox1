package com.example.karlo.toolbox;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Converter extends AppCompatActivity {

    String [] units;

    final Context context = this;
    int pos = 0;
    float lastValue = 0;

    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setLogo(R.mipmap.toolsab);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final TextView tvPretvorba = (TextView)findViewById(R.id.tvPretvorba);
        Spinner spin = (Spinner)findViewById(R.id.unit_spinner);
        Button btnConvert = (Button)findViewById(R.id.btnConvert);
        final EditText txtEntry = (EditText)findViewById(R.id.txtEntry);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(
                this, R.array.unit_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(
                    AdapterView<?> parent, View view, int position, long id) {
                    pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (txtEntry.getText().toString().trim().length() > 0){
                    String textValue = txtEntry.getText().toString();
                    lastValue = Float.parseFloat(textValue);
                    double km, m, C, K, kg, lb;


                    if(pos==0){
                        km = lastValue / 1.61;
                         km = Math.round(km *100.0)/100.0;
                        tvPretvorba.setText(km + " km");
                    }else if(pos==1){
                        m = lastValue * 1.61;
                        m = Math.round(m *100.0)/100.0;
                        tvPretvorba.setText(m + " milje");
                    }else if(pos==2){
                        K = lastValue + 273.15;
                        K = Math.round(K *100.0)/100.0;
                        tvPretvorba.setText(K + "K");
                    }
                    else if(pos==3) {
                        C = lastValue - 273.15;
                        C = Math.round(C *100.0)/100.0;
                        tvPretvorba.setText(C + " °C");
                    }
                    else if(pos==4) {
                        lb = lastValue * 2.2;
                        lb = Math.round(lb *100.0)/100.0;
                        tvPretvorba.setText(lb + " lb");
                    }
                    else if(pos==5) {
                        kg = lastValue / 2.2;
                        kg = Math.round(kg *100.0)/100.0;
                        tvPretvorba.setText(kg + " kg");
                    }

                    }
                else{
                    showToast("Unesite vrijednost");

                }

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.Settings).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
