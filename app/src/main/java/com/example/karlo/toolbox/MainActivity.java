package com.example.karlo.toolbox;

import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ListView;
        import android.widget.TableLayout;
        import android.widget.Toast;

        import com.google.android.gms.location.LocationSettingsRequest;

import static com.example.karlo.toolbox.R.mipmap.toolsab;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    final Context context = this;


    ListView list;
    String[] itemname = {

            "Brzinomjer",
            "E-pošta",
            "Kalkulator",
            "Pretvarač",
            "Snimač zvuka",
            "Svjetiljka",
            "Štoperica"
    };


    String[] subtxt = {


            "Mjeri brzinu i put",
            "Slanje e-pošte",
            "Osnovni kalkulator",
            "Pretvaranje mjernih jedinica",
            "Jednostavni snimač zvuka",
            "Svjetlo",
            "Prolazno vrijeme"
    };


    Integer[] imgid = {
            R.drawable.speed,
            R.drawable.email,
            R.drawable.calc,
            R.drawable.unit,
            R.drawable.recorder,
            R.drawable.flash,
            R.drawable.stopw
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_tab);

        CustomListAdapter adapter = new CustomListAdapter(this, itemname, subtxt, imgid);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setLogo(toolsab);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        // tabs to display in actionbar
        actionBar.addTab(actionBar.newTab().setText("Alati").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Lokacija").setTabListener(this));
        list.setOnItemClickListener(new OnItemClickListener()


        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tools = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(MainActivity.this, tools, Toast.LENGTH_LONG).show();

                if (position == 0) {
                    //code specific to first list item
                    Intent intent = new Intent(MainActivity.this, Speed.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    //code specific to first list item
                    Intent intent = new Intent(MainActivity.this, Email.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    //code specific to first list item
                    Intent intent = new Intent(MainActivity.this, calculator.class);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(MainActivity.this, Converter.class);
                    startActivity(intent);
                }
                if (position == 4) {
                    //code specific to first list item
                    Intent intent = new Intent(MainActivity.this, Recorder.class);
                    startActivity(intent);
                }
                if (position == 5) {
                    //code specific to first list item
                    Intent intent = new Intent(MainActivity.this, Flashlight.class);
                    startActivity(intent);
                }
                if (position == 6) {
                    //code specific to first list item
                    Intent intent = new Intent(MainActivity.this, StopWatch.class);
                    startActivity(intent);
                }






            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

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


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        int onTabSelected = tab.getPosition();

        switch (onTabSelected) {
            case 0:
                setContentView(R.layout.action_tab);
                CustomListAdapter adapter = new CustomListAdapter(this, itemname, subtxt, imgid);
                list = (ListView) findViewById(R.id.list);
                list.setAdapter(adapter);
                break;
            case 1:
                setContentView(R.layout.action_tab2);
                break;

        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {


    }


}