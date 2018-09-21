package com.gecko.meldezettel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtonClickListeners();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void setButtonClickListeners(){
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

        final ImageButton imgRei =(ImageButton)findViewById(R.id.btn_rei);
        imgRei.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent act_rei = new Intent(view.getContext(),Rei.class);
                act_rei.putExtra("reip","rei");
                startActivity(act_rei);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent act_rep = new Intent(this,AppPreferences.class);
                startActivity(act_rep);
                return true;
            case R.id.action_refresh:
                Intent refresh = new Intent(this.getApplicationContext(), DataCacher.class);
                startActivity(refresh);
                return true;
            }
        return super.onOptionsItemSelected(item);
    }

}
