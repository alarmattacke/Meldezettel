package com.gecko.meldezettel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by adnan on 21.04.15.
 */

public class Text extends Activity{

    private static final int CAMERA_REQUEST = 1;
    File myfile;

    String bedarf;
    String absender;
    String task_force;
    String arbeiten ;
    String standort;
    String beschreibung;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        addListenerOnSpinnerItemSelection();

        final String value = getIntent().getStringExtra("1");
        final String value1 = getIntent().getStringExtra("2");
        final ImageButton img_btn_back = (ImageButton) findViewById(R.id.btn_back);


        if (value.equals("rep")) {
            img_btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent act_back = new Intent(view.getContext(), Rep.class);
                    act_back.putExtra("reip","rep");
                    startActivity(act_back);
                }
            });
        }else if (value.equals("rei")){
            img_btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent act_back = new Intent(view.getContext(), Rei.class);
                    act_back.putExtra("reip","rei");
                    startActivity(act_back);
                }
            });
        }

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
            bedarf = "Reperatur" + "\n";
            arbeiten = "Sanitäre Arbeit" + "\n";
            task_force = "ISS CH Basel Facility Koordinatoren" + "\n";
        }else if (value1.equals("tuerfenster")){
            dynamic.setImageResource(R.drawable.repk_tuerfenster);
            bedarf = "Reperatur" + "\n";
            arbeiten = "Tür/Fenster Arbeit" + "\n";
            task_force = "ISS CH Basel Facility Koordinatoren" + "\n";
        }else if (value1.equals("behaelter")){
            dynamic.setImageResource(R.drawable.repk_behaelter);
            bedarf = "Reperatur" + "\n";
            arbeiten = "Behälter Arbeit" + "\n";
            task_force = "ISS CH Basel Facility Koordinatoren" + "\n";
        }else if (value1.equals("lampe")){
            dynamic.setImageResource(R.drawable.repk_lampe);
            bedarf = "Reperatur" + "\n";
            arbeiten = "Lampe / Licht Arbeit" + "\n";
            task_force = "ISS CH Basel Facility Koordinatoren" + "\n";
        }else if (value1.equals("sonstige")){
            dynamic.setImageResource(R.drawable.repk_sonstige);
            bedarf = "Reperatur" + "\n";
            arbeiten = "sonstige Arbeit" + "\n";
            task_force = "ISS CH Basel Facility Koordinatoren" + "\n";
        }else if (value1.equals("schloss")){
            dynamic.setImageResource(R.drawable.repk_verschlossen);
            bedarf = "Reperatur" + "\n";
            arbeiten = "Verschlossen" + "\n";
            task_force = "ISS CH Basel Facility Koordinatoren" + "\n";
        }else if (value1.equals("tuer")){
            dynamic.setImageResource(R.drawable.reik_tuer);
            bedarf = "Reinigung" + "\n";
            arbeiten = "Tür/Fensterreinigung" + "\n";
            task_force = "ISS CH Basel Cleaning" + "\n";
        }else if (value1.equals("spezial")){
            dynamic.setImageResource(R.drawable.reik_spezail);
            bedarf = "Reinigung" + "\n";
            arbeiten = "Spezialreinigung" + "\n";
            task_force = "ISS CH Basel Cleaning" + "\n";
        }else if (value1.equals("boden")){
            dynamic.setImageResource(R.drawable.reik_boden);
            bedarf = "Reinigung" + "\n";
            arbeiten = "Bodenreinigung" + "\n";
            task_force = "ISS CH Basel Cleaning"  + "\n";
        }else if (value1.equals("hoch")){
            dynamic.setImageResource(R.drawable.reik_hoch);
            bedarf = "Reinigung" + "\n";
            arbeiten = "Arbeiten in der Höhe" + "\n";
            task_force = "ISS CH Basel Cleaning" + "\n";
        }


        final ImageButton img_btn_next = (ImageButton) findViewById(R.id.btn_next);
        img_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Spinner spin = (Spinner) findViewById(R.id.site_spinner);

                if (!spin.getItemAtPosition(spin.getSelectedItemPosition()).toString().equals("Areal auswählen!")) {
                    Intent act_next = new Intent(view.getContext(), Cam.class);

                    final EditText loc = (EditText) findViewById(R.id.txt_location);
                    final EditText sum = (EditText) findViewById(R.id.txt_summary);

                    beschreibung = sum.getText().toString();
                    standort = spin.getItemAtPosition(spin.getSelectedItemPosition()).toString() + loc.getText().toString();

                    act_next.putExtra("1", value);
                    act_next.putExtra("2", value1);
                    act_next.putExtra("bedarf", bedarf);
                    act_next.putExtra("task_force", task_force);
                    act_next.putExtra("arbeiten", arbeiten);
                    act_next.putExtra("standort", standort);
                    act_next.putExtra("beschreibung", beschreibung);
                    startActivity(act_next);
                }
                else
                {
                    Toast.makeText(Text.this, "Areal ist pflicht!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void addListenerOnSpinnerItemSelection(){

        ArrayList<String> array = new ArrayList<String>();
        array.add("Areal auswählen!");
        array.add("WSJ-");
        array.add("WKL-");
        array.add("WSH-");
        array.add("WRO-");
        array.add("WST-");

        Spinner spinner1;
        ArrayAdapter<String> mAdapter;
        spinner1= (Spinner) findViewById(R.id.site_spinner);
        mAdapter = new ArrayAdapter<String>(this, R.layout.my_spinner, array);
        spinner1.setAdapter(mAdapter);

    }
}
