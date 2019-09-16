package de.codereddev.architecture.model;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Random;

/**
 * This will simulate a data repository that could retrieve data
 * from local or network providers in an actual implementation.
 * <p>
 * This repository is only implemented as Singleton for simulation reasons.
 */
public class NamePairRepository {

    private static final String[] firstNames = {"Kathrin", "Carl", "Denise", "Albert", "Melanie", "Tom"};
    private static final String[] lastNames = {"Smith", "Jordan", "Sarkozy", "McDonald", "Pillepalle", "Bacon"};

    private static NamePairRepository instance = null;

    public static NamePairRepository getInstance() {
        if (instance == null) {
            instance = new NamePairRepository();
        }
        return instance;
    }

    private MutableLiveData<NamePair> namePair;

    private Random random = new Random();

    private NamePairRepository() {
        namePair = new MutableLiveData<>(new NamePair(firstNames[0], lastNames[0]));
    }

    public LiveData<NamePair> getNamePair() {
        return namePair;
    }

    public void updateFirstName() {
        new Handler().postDelayed(() -> {
            NamePair newPair = new NamePair(firstNames[random.nextInt(firstNames.length)], namePair.getValue().getLastName());
            namePair.postValue(newPair);
        }, 1000);
    }

    public void updateLastName() {
        new Handler().postDelayed(() -> {
            NamePair newPair = new NamePair(namePair.getValue().getFirstName(), lastNames[random.nextInt(lastNames.length)]);
            namePair.postValue(newPair);
        }, 1000);
    }
}
