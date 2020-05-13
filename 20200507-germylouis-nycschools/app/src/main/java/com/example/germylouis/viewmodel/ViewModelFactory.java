package com.example.germylouis.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.germylouis.network.Repository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository mRepository;

    public ViewModelFactory(Repository repository) {

        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        return (T) new SchoolViewModel(mRepository);
    }
}
