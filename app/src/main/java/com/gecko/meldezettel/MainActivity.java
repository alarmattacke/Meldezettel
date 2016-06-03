package com.gecko.meldezettel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton imgRep =(ImageButton)findViewById(R.id.btn_rep);
        imgRep.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent act_rep = new Intent(view.getContext(), Rep.class);
                act_rep.putExtra("reip", "rep");
                startActivity(act_rep);

            }
        });

        final ImageButton imgInfo =(ImageButton)findViewById(R.id.info);
        imgInfo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_main_info = new Intent(view.getContext(),MainInfo.class);
                startActivity(act_main_info);

            }
        });

        imgRep.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "Reparaturbedarf", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        final ImageButton imgSettings =(ImageButton)findViewById(R.id.settings);
        imgSettings.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_rep = new Intent(view.getContext(),AppPreferences.class);
                startActivity(act_rep);

            }
        });

        final ImageButton imgRei =(ImageButton)findViewById(R.id.btn_rei);
        imgRei.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_rei = new Intent(view.getContext(),Rei.class);
                act_rei.putExtra("reip","rei");
                startActivity(act_rei);

            }
        });


        imgRei.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "Reinigungsbedarf", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
