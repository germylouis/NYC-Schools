package com.example.germylouis.network;


import com.example.germylouis.model.SchoolResults;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    public Observable<List<SchoolResults>> getSchoolsList() {

        return new RetrofitInstance()
                .getSchools()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}