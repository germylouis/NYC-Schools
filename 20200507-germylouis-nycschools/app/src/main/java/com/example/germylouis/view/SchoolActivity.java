package com.example.germylouis.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.germylouis.R;
import com.example.germylouis.adapter.SchoolAdapter;
import com.example.germylouis.model.SchoolResults;
import com.example.germylouis.network.Repository;
import com.example.germylouis.viewmodel.SchoolViewModel;
import com.example.germylouis.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class SchoolActivity extends AppCompatActivity implements SchoolAdapter.SchoolSelectDelegate {

    private List<SchoolResults> schoolList = new ArrayList<>();
    private SchoolFragment schoolFragment = new SchoolFragment();
    private Observer<Boolean> resultObserver;
    private SchoolViewModel mainViewModel;
    private RecyclerView listof;

    public SchoolAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_activity);

        listof = findViewById(R.id.listof_schools);
        mainViewModel = new ViewModelProvider(this, new ViewModelFactory(new Repository())).get(SchoolViewModel.class);

        getSchools();

        mAdapter = new SchoolAdapter(schoolList, this);
        listof.setAdapter(mAdapter);
    }

    private void getSchools() {

        mainViewModel.getSchools();
        schoolList = mainViewModel.returnSchool;
        resultObserver = new Observer<Boolean>() {

            @Override
            public void onChanged(Boolean aBoolean) {
                setRecyclerView(mainViewModel.returnSchool);
            }
        };

        mainViewModel.schoolsReturned.observe(this, resultObserver);
    }

    private void setRecyclerView(List<SchoolResults> schoolResults) {

        mAdapter.schoolList = schoolResults;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectSchool(SchoolResults schoolResult) {

        Bundle schoolBundle = new Bundle();
        FragmentManager fm = getSupportFragmentManager();
        schoolBundle.putParcelable("school_results", schoolResult);

        schoolFragment.setArguments(schoolBundle);

        if (schoolFragment.isAdded()) {
            fm.beginTransaction().remove(schoolFragment).commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_view_layout, schoolFragment)
                    .addToBackStack(schoolFragment.getTag())
                    .commit();
        }
    }
}
