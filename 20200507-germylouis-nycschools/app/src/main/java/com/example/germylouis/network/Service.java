package com.example.germylouis.network;

import com.example.germylouis.model.Information;
import com.example.germylouis.model.SchoolResults;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("/resource/s3k6-pzi2.json")
    Observable<List<SchoolResults>> getSchools();

    @GET("/resource/f9bf-2cp4.json")
    Observable<List<Information>> getInformation(@Query("dbn") String schoolDBN);
}
