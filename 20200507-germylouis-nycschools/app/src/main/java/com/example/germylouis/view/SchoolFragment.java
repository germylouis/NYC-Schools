package com.example.germylouis.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.germylouis.R;
import com.example.germylouis.model.SchoolResults;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchoolFragment extends Fragment {

    @BindView(R.id.frag_poster_view)
    ImageView posterImage;

    @BindView(R.id.frag_title_tv)
    TextView titleTextView;

    @BindView(R.id.frag_score_tv)
    TextView scoreTextView;

    @BindView(R.id.frag_date_tv)
    TextView dateTextView;

    @BindView(R.id.frag_overview_tv)
    TextView overviewTextView;

    @BindView(R.id.frag_background_iv)
    ImageView backgroundImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.school_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        SchoolResults schoolResults = bundle.getParcelable("school_results");



        Glide.with(view.getContext().getApplicationContext())
                .load(IMAGE_URL + movie.getPosterPath())
                .into(posterImage);

        titleTextView.setText(schoolResults.getSchoolName());

        Glide.with(view.getContext().getApplicationContext())
                .load(IMAGE_URL + movie.getBackdropPath())
                .into(backgroundImageView);

    }
}


