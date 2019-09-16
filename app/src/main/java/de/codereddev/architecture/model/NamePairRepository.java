package de.codereddev.architecture.model;

import android.os.Handler;

import java.util.Random;

import io.reactivex.Observable;

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

    private NamePair namePair;

    private Random random = new Random();

    private NamePairRepository() {
        namePair = new NamePair(firstNames[0], lastNames[0]);
    }

    public Observable<NamePair> getNamePair() {
        return Observable.just(namePair);
    }

    public void updateFirstName() {
        new Handler().postDelayed(() ->
                        namePair.setFirstName(firstNames[random.nextInt(firstNames.length)]),
                1000);
    }

    public void updateLastName() {
        new Handler().postDelayed(() ->
                        namePair.setLastName(lastNames[random.nextInt(lastNames.length)]),
                1000);
    }
}
