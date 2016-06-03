package com.gecko.meldezettel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by adnan on 21.04.15.
 */

public class Text extends Activity {

    private static final int CAMERA_REQUEST = 1;
    File myfile;

    String bedarf;
    String absender;
    String task_force;
    String arbeiten;
    String standort;
    String beschreibung;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);


        final String value = getIntent().getStringExtra("1");
        final String value1 = getIntent().getStringExtra("2");
        final ImageButton img_btn_back = (ImageButton) findViewById(R.id.btn_back);


        if (value.equals("rep")) {
            img_btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent act_back = new Intent(view.getContext(), Rep.class);
                    act_back.putExtra("reip", "rep");
                    startActivity(act_back);
                }
            });
        } else if (value.equals("rei")) {
            img_btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent act_back = new Intent(view.getContext(), Rei.class);
                    act_back.putExtra("reip", "rei");
                    startActivity(act_back);
                }
            });
        }

        final ImageButton first = (ImageButton) findViewById(R.id.first);
        if (value.equals("rei")) {
            first.setImageResource(R.drawable.reinigung_klein);
        } else if (value.equals("rep")) {
            first.setImageResource(R.drawable.repk);
        }

        final String text = "";
        final ImageButton dynamic = (ImageButton) findViewById(R.id.dynamic);

        if (value1.equals("sanitaer")) {
            dynamic.setImageResource(R.drawable.repk_sanitaer);
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.sanitary) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("tuerfenster")) {
            dynamic.setImageResource(R.drawable.repk_tuerfenster);
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.window_door) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("behaelter")) {
            dynamic.setImageResource(R.drawable.repk_behaelter);
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.container) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("lampe")) {
            dynamic.setImageResource(R.drawable.repk_lampe);
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.lamp) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("sonstige")) {
            dynamic.setImageResource(R.drawable.repk_sonstige);
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.other) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("schloss")) {
            dynamic.setImageResource(R.drawable.repk_verschlossen);
            bedarf = getResources().getString(R.string.bedarf_repair)+ "\n";
            arbeiten = getResources().getString(R.string.sealed) + "\n";
            task_force = getResources().getString(R.string.task_force_rep) + "\n";
        } else if (value1.equals("tuer")) {
            dynamic.setImageResource(R.drawable.reik_tuer);
            bedarf = getResources().getString(R.string.bedarf_repair) + "\n";
            arbeiten = getResources().getString(R.string.window_door_rei) + "\n";
            task_force = getResources().getString(R.string.task_force_rei) + "\n";
        } else if (value1.equals("spezial")) {
            dynamic.setImageResource(R.drawable.reik_spezail);
            bedarf = getResources().getString(R.string.bedarf_cleaning) + "\n";
            arbeiten = getResources().getString(R.string.special) + "\n";
            task_force = getResources().getString(R.string.task_force_rei) + "\n";
        } else if (value1.equals("boden")) {
            dynamic.setImageResource(R.drawable.reik_boden);
            bedarf = getResources().getString(R.string.bedarf_cleaning) + "\n";
            arbeiten = getResources().getString(R.string.floor) + "\n";
            task_force = getResources().getString(R.string.task_force_rei) + "\n";
        } else if (value1.equals("hoch")) {
            dynamic.setImageResource(R.drawable.reik_hoch);
            bedarf = getResources().getString(R.string.bedarf_cleaning) + "\n";
            arbeiten = getResources().getString(R.string.hight) + "\n";
            task_force = getResources().getString(R.string.task_force_rei) + "\n";
        }


        final ImageButton img_btn_next = (ImageButton) findViewById(R.id.btn_next);
        img_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act_next = new Intent(view.getContext(), Cam.class);

                final EditText loc = (EditText) findViewById(R.id.txt_location);
                final EditText sum = (EditText) findViewById(R.id.txt_summary);

                beschreibung = sum.getText().toString();
                standort = loc.getText().toString();

                act_next.putExtra("1", value);
                act_next.putExtra("2", value1);
                act_next.putExtra("bedarf", bedarf);
                act_next.putExtra("task_force", task_force);
                act_next.putExtra("arbeiten", arbeiten);
                act_next.putExtra("standort", standort);
                act_next.putExtra("beschreibung", beschreibung);
                startActivity(act_next);
            }
        });
    }

}
