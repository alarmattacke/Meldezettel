package com.gecko.meldezettel;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gecko.meldezettel.db.DataSource;
import com.gecko.meldezettel.json.APIPlug;
import com.gecko.meldezettel.json.ApiClient;
import com.gecko.meldezettel.json.Building;
import com.gecko.meldezettel.json.Mandate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by alarmattacke on 29.09.16.
 */

public class DataCacher extends AppCompatActivity {

    private static final String LOG_TAG = DataCacher.class.getSimpleName();
    private DataSource dataSource;
    private List<Mandate> mandates;
    public String base_url = null;
    public String mandate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_cacher);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Button GetServerData = (Button) findViewById(R.id.get_data);
        ImageView view = (ImageView) findViewById(R.id.imageView12);
        view.setImageResource(R.drawable.white);
        GetServerData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                getBuildingsList();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getBuildingsList() {

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        base_url = SP.getString("serviceURL", "");
        mandate = SP.getString("mandate", "");
        deleteData();
        StringTokenizer tokens = new StringTokenizer(mandate, ":");
        while (tokens.hasMoreTokens()) {
            getBuildingsbyMandate(tokens.nextToken());
        }
    }

    private void getBuildingsbyMandate(String mandate) {
        final ProgressDialog loading = ProgressDialog.show(this, "Load data", "loading...");
        final ImageView view = (ImageView) findViewById(R.id.imageView12);

        APIPlug apiService = ApiClient.getClient(base_url).create(APIPlug.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put("code", mandate);

        Call<List<Mandate>> call = apiService.getMandates(params);

        call.enqueue(new Callback<List<Mandate>>()
            {
                @Override
                public void onFailure(Call<List<Mandate>> call, Throwable t) {
                    view.setImageResource(R.drawable.not_ok_icon);
                    Toast.makeText(getApplication().getBaseContext(), "Error occured", Toast.LENGTH_LONG).show();
                    loading.dismiss();
                }

                @Override
                public void onResponse
                        (Call<List<Mandate>> call, Response<List<Mandate>> response) {
                    mandates = response.body();
                    if (mandates != null) {
                        if (mandates.size() > 0) {
                            insertData(mandates);
                            view.setImageResource(R.drawable.ok_icon);
                            loading.dismiss();
                        } else {
                            view.setImageResource(R.drawable.not_ok_icon);
                            Toast.makeText(getApplication().getBaseContext(), "No item found", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    } else {
                        view.setImageResource(R.drawable.not_ok_icon);
                        Toast.makeText(getApplication().getBaseContext(), "Check the serivceURL in settings", Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }
                }
            }
        );
    }

    public void insertData(List<Mandate> jsonMandateList) {
        dataSource = new DataSource(this);
        dataSource.open();

        for (int count = 0; count < jsonMandateList.size(); count++) {
            String mandate_code = jsonMandateList.get(count).getCode();
            String mandate_name = jsonMandateList.get(count).getName();
            List<Building> buildings = jsonMandateList.get(count).getBuildings();
            com.gecko.meldezettel.db.Mandate mandate = dataSource.createMandate(mandate_code, mandate_name);

            for (int i = 0; i < buildings.size(); i++) {
                if (buildings.get(i).getName() != null && buildings.get(i).getCode() != null) {
                    String building_code = buildings.get(i).getCode();
                    String building_name = buildings.get(i).getName();
                    long mandate_id = mandate.getId();
                    com.gecko.meldezettel.db.Building building = dataSource.createBuilding(building_code, building_name, mandate_id);
                }
            }
        }

        dataSource.close();
        Toast.makeText(getApplication().getBaseContext(), "done!", Toast.LENGTH_LONG).show();
    }

    public void deleteData() {
        dataSource = new DataSource(this);
        dataSource.open();
        dataSource.emptyDbTables();
        dataSource.close();
    }

}