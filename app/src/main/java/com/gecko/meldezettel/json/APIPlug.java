package com.gecko.meldezettel.json;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by alarmattacke on 11.10.16.
 */
public interface APIPlug {
    @GET("mandates")
    Call<List<Mandate>> getMandates(@QueryMap Map<String, String> params);
}
