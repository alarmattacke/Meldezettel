package com.gecko.meldezettel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by alarmattacke on 20.04.15.
 */

public class Rep extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rep);
        final String value = getIntent().getStringExtra("reip");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
