package com.gecko.meldezettel;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.gecko.meldezettel.db.Building;
import com.gecko.meldezettel.db.DataSource;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import net.yazeed44.imagepicker.model.ImageEntry;
import net.yazeed44.imagepicker.util.Picker;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.v4.content.FileProvider.*;


/**
 * Created by adnan on 21.04.15.
 */

public class Text extends AppCompatActivity implements Picker.PickListener{
    private static final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 9999;

    String bedarf;
    String task_force;
    String arbeiten;
    String standort;
    String beschreibung;
    ArrayList<Uri> uriList = new ArrayList<Uri>();

    private DataSource dataSource;
    public static final String LOG_TAG = Text.class.getSimpleName();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        dataSource = new DataSource(this);
        dataSource.open();
        showAllListEntries();
        dataSource.close();

        final String value = getIntent().getStringExtra("1");
        final String value1 = getIntent().getStringExtra("2");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton photoButton = (ImageButton) this.findViewById(R.id.btn_foti);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });


        if (value1.equals("sanitaer")) {
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.sanitary) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("tuerfenster")) {
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.window_door) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("behaelter")) {
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.container) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("lampe")) {
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.lamp) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("sonstige")) {
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.other) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("schloss")) {
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.sealed) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("tuer")) {
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.window_door_rei) + "\n";
            task_force = getResources().getString(R.string.task_force_rei) + "\n";
        } else if (value1.equals("spezial")) {
            bedarf = getResources().getString(R.string.bedarf_cleaning) + "\n";
            arbeiten = getResources().getString(R.string.special) + "\n";
            task_force = getResources().getString(R.string.task_force_rei) + "\n";
        } else if (value1.equals("boden")) {
            bedarf = getResources().getString(R.string.bedarf_cleaning) + "\n";
            arbeiten = getResources().getString(R.string.floor) + "\n";
            task_force = getResources().getString(R.string.task_force_rei) + "\n";
        } else if (value1.equals("hoch")) {
            bedarf = getResources().getString(R.string.bedarf_cleaning) + "\n";
            arbeiten = getResources().getString(R.string.hight) + "\n";
            task_force = getResources().getString(R.string.task_force_rei) + "\n";
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_mail:
                sendEmail();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void sendEmail() {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        String abs_email = SP.getString("absender", "");
        String[] TO = {SP.getString("empfaenger1", "")};
        String[] CC = {SP.getString("empfaenger2", "")};


        final EditText loc = (EditText) findViewById(R.id.autoCompleteLocation);
        final EditText sum = (EditText) findViewById(R.id.txt_summary);

        beschreibung = sum.getText().toString();
        standort = loc.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject));

        String content = getResources().getString(R.string.content0) + "\n\n\n";
        content += getResources().getString(R.string.content1) + "\n\n";
        content += getResources().getString(R.string.content2) + "  Meldezettel" + "\n\n";
        content += getResources().getString(R.string.content3) + "  " + abs_email + "\n\n";
        content += getResources().getString(R.string.content4) + "  " + bedarf + "\n";
        content += getResources().getString(R.string.content5) + "  " + task_force + "\n";
        content += getResources().getString(R.string.content6) + "  " + arbeiten + "\n";
        content += getResources().getString(R.string.content7) + "  " + standort + "\n";
        content += getResources().getString(R.string.content8) + "  " + beschreibung + "\n";

        emailIntent.putExtra(Intent.EXTRA_TEXT, content);
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if (uriList.size() > 0) {
            emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
        }

        try {
            startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.send_mail)));
            finish();
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(Text.this,
                    getResources().getString(R.string.no_mail_client), Toast.LENGTH_SHORT).show();
        }
    }

    private void dispatchTakePictureIntent() {


            List<String> permissionsNeeded = new ArrayList<String>();

            final List<String> permissionsList = new ArrayList<String>();
            if (!addPermission(permissionsList, Manifest.permission.CAMERA))
                permissionsNeeded.add("Cam");
            if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
                permissionsNeeded.add("Read from storage");
            if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                permissionsNeeded.add("Write to storage");

            if (permissionsList.size() > 0) {
                if (permissionsNeeded.size() > 0) {
                    // Need Rationale
                    String message = "You need to grant access to " + permissionsNeeded.get(0);
                    for (int i = 1; i < permissionsNeeded.size(); i++)
                        message = message + ", " + permissionsNeeded.get(i);
                    showMessageOKCancel(message, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);

                        }
                    });
                    return;
                }
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                return;
            }

        startPicker();
    }

    private boolean addPermission(List<String> permissionsList, String permission) {

            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                if (!shouldShowRequestPermissionRationale(permission))
                    return false;
            }

        return true;
    }



    private void startPicker(){
        new Picker.Builder(this,this,R.style.MIP_theme)
        .setPickMode(Picker.PickMode.MULTIPLE_IMAGES)
            .setLimit(5)
            .build()
            .startActivity();
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
            {
                Map<String, Integer> perms = new HashMap<String, Integer>();

                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);

                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);

                if (perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    startPicker();
                } else {

                    Toast.makeText(this, "Some permission is denied", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onPickedSuccessfully(final ArrayList<ImageEntry> images) {
        for(int i = 0; i < images.size(); i++)
        {
            String path = images.get(i).path;
            File new_file = new File(path);
            Uri uri = getUriForFile(Text.this, BuildConfig.APPLICATION_ID, new File(images.get(i).path));
            uriList.add(i, uri);
        }
    }

    @Override
    public void onCancel() {
        //User canceled the pick activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showAllListEntries() {
        List<Building> buildingList = dataSource.getAllBuildings();
        ArrayAdapter<Building> buildingMemoArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                buildingList);

        AutoCompleteTextView buildingMemosAutoCompleteView = (AutoCompleteTextView) findViewById(R.id.autoCompleteLocation);
        buildingMemosAutoCompleteView.setAdapter(buildingMemoArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_text, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Text Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }



}
