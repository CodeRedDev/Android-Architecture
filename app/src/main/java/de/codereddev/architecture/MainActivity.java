package de.codereddev.architecture;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.codereddev.architecture.model.NamePair;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView firstNameTv;
    private TextView lastNameTv;
    private ProgressBar progressBar;

    private CompositeDisposable compositeDisposable;
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
        updateFirstFab.setOnClickListener(view -> {
                    progressBar.setVisibility(View.VISIBLE);
                    viewModel.updateFirstName();
                }
        );

        FloatingActionButton updateLastFab = findViewById(R.id.updateLastFab);
        updateLastFab.setOnClickListener(view -> {
                    progressBar.setVisibility(View.VISIBLE);
                    viewModel.updateLastName();
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.getNamePair()
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::setNames));
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }

    private void setNames(@NonNull final NamePair currentPair) {
        progressBar.setVisibility(View.GONE);
        firstNameTv.setText(currentPair.getFirstName());
        lastNameTv.setText(currentPair.getLastName());
    }
}
