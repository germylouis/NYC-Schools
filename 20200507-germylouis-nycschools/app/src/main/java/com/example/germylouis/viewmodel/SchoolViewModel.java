package com.example.germylouis.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.germylouis.adapter.SchoolAdapter;
import com.example.germylouis.model.SchoolResults;
import com.example.germylouis.network.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SchoolViewModel extends ViewModel {

    private Repository mRepository = new Repository();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<Boolean> schoolsReturned = new MutableLiveData<Boolean>();
    public List<SchoolResults> returnSchool = new ArrayList<>();

    SchoolViewModel(Repository repository) {
        this.mRepository = repository;
    }

    public void getSchools() {

        compositeDisposable.add(mRepository.getSchoolsList().subscribe(schoolResults -> {

            returnSchool = schoolResults;
            schoolsReturned.setValue(true);

        }, throwable -> {
            Log.d("TAG_X", "getSchools in adapter: " + throwable.getMessage());
        }));

    }
}
