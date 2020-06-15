package com.example.germylouis.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.germylouis.R;
import com.example.germylouis.model.Information;
import com.example.germylouis.model.SchoolResults;
import com.example.germylouis.network.Repository;
import com.example.germylouis.viewmodel.SchoolViewModel;
import com.example.germylouis.viewmodel.ViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class SchoolFragment extends Fragment {

    private SchoolViewModel schoolViewModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private SchoolResults selectedSchool;

    @BindView(R.id.fragment_school_name)
    TextView schoolNameTextView;

    @BindView(R.id.math_scores)
    TextView mathScore;

    @BindView(R.id.reading_score)
    TextView readingScore;

    @BindView(R.id.writing_score)
    TextView writingScore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.school_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        schoolViewModel = new ViewModelProvider(this, new ViewModelFactory(new Repository())).get(SchoolViewModel.class);

        if (getArguments() != null && getArguments().getParcelable("dbn_key") != null) {
            selectedSchool = getArguments().getParcelable("dbn_key");
            schoolNameTextView.setText(selectedSchool.getSchoolName());
            compositeDisposable.add(schoolViewModel.getInformation(selectedSchool.getDbn())
                    .subscribe(this::displayInfo, throwable -> {
                        Toast.makeText(getContext(), getString(R.string.error_string, throwable.getLocalizedMessage()), Toast.LENGTH_LONG).show();
                    }));
        }
    }

    private void displayInfo(Information information) {
        if (information.getSatMathAvgScore() != null)
            mathScore.setText(getString(R.string.math_string, information.getSatMathAvgScore()));
        else
            mathScore.setText(R.string.null_score);

        if (information.getSatWritingAvgScore() != null)
            writingScore.setText(getString(R.string.write_string, information.getSatWritingAvgScore()));
        else
            writingScore.setText(R.string.null_score_write);

        if (information.getSatCriticalReadingAvgScore() != null)
            readingScore.setText(getString(R.string.read_string, information.getSatCriticalReadingAvgScore()));
        else
            readingScore.setText(R.string.null_score_read);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        compositeDisposable.clear();
    }
}
