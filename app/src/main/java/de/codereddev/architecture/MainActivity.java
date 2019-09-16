package de.codereddev.architecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView firstNameTv;
    private TextView lastNameTv;
    private ProgressBar progressBar;

    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModel();

        firstNameTv = findViewById(R.id.firstNameTv);
        lastNameTv = findViewById(R.id.lastNameTv);
        progressBar = findViewById(R.id.progressBar);

        FloatingActionButton updateFirstFab = findViewById(R.id.updateFirstFab);
        updateFirstFab.setOnClickListener(view -> viewModel.updateFirstName());

        FloatingActionButton updateLastFab = findViewById(R.id.updateLastFab);
        updateLastFab.setOnClickListener(view -> viewModel.updateLastName());
    }
}
