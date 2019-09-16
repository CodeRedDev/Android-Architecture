package de.codereddev.architecture;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView firstNameTv;
    private TextView lastNameTv;
    private ProgressBar progressBar;

    private PairViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(PairViewModel.class);

        firstNameTv = findViewById(R.id.firstNameTv);
        lastNameTv = findViewById(R.id.lastNameTv);
        progressBar = findViewById(R.id.progressBar);

        FloatingActionButton updateFirstFab = findViewById(R.id.updateFirstFab);
        updateFirstFab.setOnClickListener(view -> {
                    showProgressBar();
                    viewModel.updateFirstName();
                }
        );

        FloatingActionButton updateLastFab = findViewById(R.id.updateLastFab);
        updateLastFab.setOnClickListener(view -> {
                    showProgressBar();
                    viewModel.updateLastName();
                }
        );

        addObservers();
    }

    private void addObservers() {
        final Observer<String> firstNameObserver = firstName -> {
            hideProgressBar();
            firstNameTv.setText(firstName);
        };
        viewModel.getFirstName().observe(this, firstNameObserver);

        final Observer<String> lastNameObserver = lastName -> {
            hideProgressBar();
            lastNameTv.setText(lastName);
        };
        viewModel.getLastName().observe(this, lastNameObserver);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
