package com.gecko.meldezettel;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Cam extends Activity {

    private static final int CAMERA_REQUEST = 1;
    File myfile;

    String bedarf;
    String absender;
    String task_force;
    String arbeiten ;
    String standort;
    String beschreibung;
    String value;
    String value1;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam);

        bedarf = getIntent().getStringExtra("bedarf");
        task_force = getIntent().getStringExtra("task_force");
        arbeiten = getIntent().getStringExtra("arbeiten");
        standort = getIntent().getStringExtra("standort");
        beschreibung = getIntent().getStringExtra("beschreibung");
        value = getIntent().getStringExtra("1");
        value1 = getIntent().getStringExtra("2");

        final ImageButton first = (ImageButton) findViewById(R.id.first);
        if (value.equals("rei")) {
            first.setImageResource(R.drawable.reinigung_klein);
        }else if (value.equals("rep")){
            first.setImageResource(R.drawable.repk);
        }

        final String text = "";
        final ImageButton dynamic = (ImageButton) findViewById(R.id.dynamic);

        if (value1.equals("sanitaer")) {
            dynamic.setImageResource(R.drawable.repk_sanitaer);
        }else if (value1.equals("tuerfenster")){
            dynamic.setImageResource(R.drawable.repk_tuerfenster);
        }else if (value1.equals("behaelter")){
            dynamic.setImageResource(R.drawable.repk_behaelter);
        }else if (value1.equals("lampe")){
            dynamic.setImageResource(R.drawable.repk_lampe);
        }else if (value1.equals("sonstige")){
            dynamic.setImageResource(R.drawable.repk_sonstige);
        }else if (value1.equals("schloss")){
            dynamic.setImageResource(R.drawable.repk_verschlossen);
        }else if (value1.equals("tuer")){
            dynamic.setImageResource(R.drawable.reik_tuer);
        }else if (value1.equals("spezial")){
            dynamic.setImageResource(R.drawable.reik_spezail);
        }else if (value1.equals("boden")){
            dynamic.setImageResource(R.drawable.reik_boden);
        }else if (value1.equals("hoch")){
            dynamic.setImageResource(R.drawable.reik_hoch);
        }

        final ImageButton img_btn_back = (ImageButton) findViewById(R.id.btn_back);
        img_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act_back = new Intent(view.getContext(), Text.class);
                act_back.putExtra("1", value);
                act_back.putExtra("2", value1);

                startActivity(act_back);
            }
        });

        ImageButton photoButton = (ImageButton) this.findViewById(R.id.btn_foti);

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        ImageButton img_btn_mail = (ImageButton) this.findViewById(R.id.btn_mail);
        img_btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data.getExtras() != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            File imagesFolder;
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            if (photo != null) {
                ImageView bild = (ImageView)findViewById(R.id.bild);
                bild.setImageBitmap(photo);
                photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                        imagesFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/MyAppName");
                    } else {
                        imagesFolder = new File(Environment.getExternalStorageDirectory() + "/dcim/" + "MyAppName");
                    }
                    if (!imagesFolder.exists()) {
                        imagesFolder.mkdirs();
                    }

                    try {
                        myfile = File.createTempFile("Image_Name", ".jpeg", imagesFolder);
                        FileOutputStream out = new FileOutputStream(myfile);
                        out.write(bytes.toByteArray());
                        out.flush();
                        out.close();
                        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                        Uri contentUri = Uri.fromFile(myfile);
                        mediaScanIntent.setData(contentUri);
                        this.sendBroadcast(mediaScanIntent);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                } else {
                    imagesFolder = new File("/storage/emmc/dcim/100MEDIA" + "MyAppName");

                    if (!imagesFolder.exists()) {
                        imagesFolder.mkdirs();
                    }

                    try {
                        myfile = File.createTempFile("Image_Name", ".jpeg", imagesFolder);
                        FileOutputStream out = new FileOutputStream(myfile);

                        out.write(bytes.toByteArray());
                        out.flush();
                        out.close();

                        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");

                        Uri contentUri = Uri.fromFile(myfile);
                        mediaScanIntent.setData(contentUri);
                        this.sendBroadcast(mediaScanIntent);

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        else
        {
            return;
        }
    }

    protected void sendEmail() {
<<<<<<< HEAD
        Log.i("Sende Mail", "");
=======
>>>>>>> 9e3576dc9b0ac2772e4c9c012a3f5f3cdb923c26
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        String abs_email = SP.getString("absender", "");
        String[] TO = {SP.getString("empfaenger1", "")};
        String[] CC = {SP.getString("empfaenger2", "")};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject));

        String content = getResources().getString(R.string.content0) + "\n\n\n";
        content += getResources().getString(R.string.content1) + "\n\n" ;
        content += getResources().getString(R.string.content2) + "  Meldezettel" + "\n\n";
        content += getResources().getString(R.string.content3) + "  " + abs_email + "\n\n";
        content += getResources().getString(R.string.content4) + "  " + bedarf + "\n";
        content += getResources().getString(R.string.content5) + "  " + task_force + "\n";
        content += getResources().getString(R.string.content6) + "  " + arbeiten + "\n";
        content += getResources().getString(R.string.content7) + "  " + standort + "\n";
        content += getResources().getString(R.string.content8) + "  " + beschreibung + "\n";

        emailIntent.putExtra(Intent.EXTRA_TEXT, content);
        emailIntent.setType("image/jpeg");

        if (myfile != null) {
            Uri myUri = Uri.fromFile(myfile);
            emailIntent.putExtra(Intent.EXTRA_STREAM, myUri);
        }

        try {
            startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.send_mail)));

            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Cam.this,
                    getResources().getString(R.string.no_mail_client), Toast.LENGTH_SHORT).show();
        }
    }
}


















