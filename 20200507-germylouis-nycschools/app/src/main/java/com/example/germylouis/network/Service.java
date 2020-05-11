package com.example.germylouis.network;

import com.example.germylouis.model.SchoolResults;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Service {

    @GET("/resource/s3k6-pzi2.json")
    Observable<List<SchoolResults>> getSchools();
}
