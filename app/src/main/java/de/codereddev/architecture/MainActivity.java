package de.codereddev.architecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.codereddev.architecture.model.NamePair;
import de.codereddev.architecture.model.NamePairRepository;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private TextView firstNameTv;
    private TextView lastNameTv;

    private NamePairRepository repository = NamePairRepository.getInstance();
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new Controller(this);

        firstNameTv = findViewById(R.id.firstNameTv);
        lastNameTv = findViewById(R.id.lastNameTv);

        FloatingActionButton updateFirstFab = findViewById(R.id.updateFirstFab);
        updateFirstFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.updateFirstName();
            }
        });

        FloatingActionButton updateLastFab = findViewById(R.id.updateLastFab);
        updateLastFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.updateLastName();
            }
        });

        // First load
        onModelUpdated();
    }

    @Override
    protected void onResume() {
        super.onResume();
        repository.addObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        repository.deleteObserver(this);
    }

    @Override
    public void onModelUpdated() {
        NamePair currentPair = repository.getNamePair();

        firstNameTv.setText(currentPair.getFirstName());
        lastNameTv.setText(currentPair.getLastName());
    }
}
