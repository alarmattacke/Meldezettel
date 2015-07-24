package com.gecko.meldezettel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by adnan on 20.04.15.
 */

public class Rep extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rep);
        final String value = getIntent().getStringExtra("reip");

        final ImageButton imgBack =(ImageButton)findViewById(R.id.back);
        imgBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent act_main = new Intent(view.getContext(), MainActivity.class);
                startActivity(act_main);

            }
        });


        final ImageButton imgRep =(ImageButton)findViewById(R.id.boden);
        imgRep.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_text = new Intent(view.getContext(),Text.class);
                act_text.putExtra("1",value);
                act_text.putExtra("2","behaelter");
                startActivity(act_text);

            }
        });


        final ImageButton imgRep1 =(ImageButton)findViewById(R.id.tuer);
        imgRep1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_text = new Intent(view.getContext(),Text.class);
                act_text.putExtra("2","tuerfenster");
                act_text.putExtra("1",value);
                startActivity(act_text);

            }
        });


        final ImageButton imgRep2 =(ImageButton)findViewById(R.id.sonstige);
        imgRep2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_text = new Intent(view.getContext(),Text.class);
                act_text.putExtra("2","sonstige");
                act_text.putExtra("1",value);
                startActivity(act_text);


            }
        });


        final ImageButton imgInfo =(ImageButton)findViewById(R.id.info);
        imgInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent act_main_info = new Intent(view.getContext(), RepInfo.class);
                startActivity(act_main_info);

            }
        });




        final ImageButton imgRep4 =(ImageButton)findViewById(R.id.sanitaer);
        imgRep4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_text = new Intent(view.getContext(),Text.class);
                act_text.putExtra("2","sanitaer");
                act_text.putExtra("1",value);
                startActivity(act_text);
            }
        });

        imgRep4.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "Reperatur im Sanitär-Bereich", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        final ImageButton imgRep5 =(ImageButton)findViewById(R.id.lampe);
        imgRep5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_text = new Intent(view.getContext(),Text.class);
                act_text.putExtra("2","lampe");
                act_text.putExtra("1",value);
                startActivity(act_text);
            }
        });

        imgRep5.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "kaputte Lampe", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
}
