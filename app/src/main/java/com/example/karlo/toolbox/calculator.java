
package com.example.karlo.toolbox;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.karlo.toolbox.R;
import com.google.android.gms.location.LocationSettingsRequest;


public class calculator extends AppCompatActivity implements View.OnClickListener {

    final Context context = this;
    EditText prvi_broj;
    EditText drugi_broj;

    Button plus;
    Button minus;
    Button puta;
    Button podijeljeno;

    TextView rez;

    String oper = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setLogo(R.mipmap.toolsab);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        // traženje i definiranje elemenata
        prvi_broj = (EditText) findViewById(R.id.prvi_broj);
        drugi_broj = (EditText) findViewById(R.id.drugi_broj);

        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        puta = (Button) findViewById(R.id.puta);
        podijeljeno = (Button) findViewById(R.id.podijeljeno);

        rez = (TextView) findViewById(R.id.rezultat);

        // postavljanje listener-a
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        puta.setOnClickListener(this);
        podijeljeno.setOnClickListener(this);
    }

    public void onClick (View v) {

        if (TextUtils.isEmpty(prvi_broj.getText().toString())
                || TextUtils.isEmpty(drugi_broj.getText().toString())) {
            return;
        }

        float num1 = 0;
        float num2 = 0;
        float rezultat = 0;

        // citanje EditText-a i popunjavanje varijabli sa brojevima
        num1 = Float.parseFloat(prvi_broj.getText().toString());
        num2 = Float.parseFloat(drugi_broj.getText().toString());


        switch (v.getId()) {
            case R.id.plus:
                oper = "+";
                rezultat = num1 + num2;
                break;
            case R.id.minus:
                oper = "-";
                rezultat = num1 - num2;
                break;
            case R.id.puta:
                oper = "*";
                rezultat = num1 * num2;
                break;
            case R.id.podijeljeno:
                oper = "/";
                rezultat = num1 / num2;
                break;
            default:
                break;
        }
        //ispisivanje rezultata
        rez.setText(num1 + " " + oper + " " + num2 + " = " + rezultat);
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