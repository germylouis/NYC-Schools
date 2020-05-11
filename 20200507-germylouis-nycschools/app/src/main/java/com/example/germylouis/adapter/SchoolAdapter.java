package com.example.germylouis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.germylouis.R;
import com.example.germylouis.model.SchoolResults;

import java.util.ArrayList;
import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder> {

    public List<SchoolResults> schoolList = new ArrayList<>();

    private SchoolSelectDelegate schoolSelectDelegate;


    public interface SchoolSelectDelegate {

        void selectSchool(SchoolResults schoolResult);
    }


    public SchoolAdapter(List<SchoolResults> schoolList, SchoolSelectDelegate schoolSelectDelegate) {

        this.schoolList = schoolList;
        this.schoolSelectDelegate = schoolSelectDelegate;
    }

    public List<SchoolResults> getSchoolList() {

        return schoolList;
    }

    @NonNull
    @Override
    public SchoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new SchoolViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listof_schools, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolViewHolder holder, int position) {

        holder.schoolNameTextView.setText(schoolList.get(position).getSchoolName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   schoolSelectDelegate.selectSchool(schoolList.get(position));
                                               }
                                           }
        );
    }

    @Override
    public int getItemCount() {

        return schoolList.size();
    }

    class SchoolViewHolder extends RecyclerView.ViewHolder {

        TextView schoolNameTextView;

        SchoolViewHolder(@NonNull View itemView) {
            super(itemView);
            schoolNameTextView = itemView.findViewById(R.id.school_name);
        }
    }
}
