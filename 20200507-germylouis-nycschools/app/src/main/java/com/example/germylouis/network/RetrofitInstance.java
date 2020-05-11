package com.example.germylouis.network;

import com.example.germylouis.model.SchoolResults;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.germylouis.util.Constants.BASE_URL;

public class RetrofitInstance {

    public Service service;

    RetrofitInstance() {

        service = createService(getInstance());
    }

    private Retrofit getInstance() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private Service createService(Retrofit retrofit) {

        return retrofit.create(Service.class);
    }

    public Observable<List<SchoolResults>> getSchools() {

        return service.getSchools();
    }


}
