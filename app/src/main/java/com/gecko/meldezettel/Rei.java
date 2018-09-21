package com.gecko.meldezettel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by adnan on 20.04.15.
 */
public class Rei extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rei);
        final String value = getIntent().getStringExtra("reip");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageButton imgRei =(ImageButton)findViewById(R.id.schloss);
        imgRei.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_text = new Intent(view.getContext(),Text.class);
                act_text.putExtra("2","schloss");
                act_text.putExtra("1",value);
                startActivity(act_text);
            }
        });


        final ImageButton imgRep =(ImageButton)findViewById(R.id.boden);
        imgRep.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_text = new Intent(view.getContext(),Text.class);
                act_text.putExtra("1",value);
                act_text.putExtra("2","boden");
                startActivity(act_text);

            }
        });


        final ImageButton imgInfo =(ImageButton)findViewById(R.id.info);
        imgInfo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_main_info = new Intent(view.getContext(),ReiInfo.class);
                startActivity(act_main_info);

            }
        });



        final ImageButton imgRep1 =(ImageButton)findViewById(R.id.tuer);
        imgRep1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_text = new Intent(view.getContext(),Text.class);
                act_text.putExtra("1",value);
                act_text.putExtra("2","tuer");
                startActivity(act_text);

            }
        });


        final ImageButton imgRep2 =(ImageButton)findViewById(R.id.spezial);
        imgRep2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_text = new Intent(view.getContext(),Text.class);
                act_text.putExtra("1",value);
                act_text.putExtra("2","spezial");
                startActivity(act_text);

            }
        });


        final ImageButton imgRep3 =(ImageButton)findViewById(R.id.hoch);
        imgRep3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_text = new Intent(view.getContext(),Text.class);
                act_text.putExtra("1",value);
                act_text.putExtra("2","hoch");
                startActivity(act_text);
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
