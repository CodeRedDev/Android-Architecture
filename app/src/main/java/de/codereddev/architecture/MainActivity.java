package de.codereddev.architecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private TextView firstNameTv;
    private TextView lastNameTv;

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new NamePairPresenter(this);

        firstNameTv = findViewById(R.id.firstNameTv);
        lastNameTv = findViewById(R.id.lastNameTv);

        FloatingActionButton updateFirstFab = findViewById(R.id.updateFirstFab);
        updateFirstFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateFirstName();
            }
        });

        FloatingActionButton updateLastFab = findViewById(R.id.updateLastFab);
        updateLastFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateLastName();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Override
    public void showFirstName(String firstName) {
        firstNameTv.setText(firstName);
    }

    @Override
    public void showLastName(String lastName) {
        lastNameTv.setText(lastName);
    }
}
