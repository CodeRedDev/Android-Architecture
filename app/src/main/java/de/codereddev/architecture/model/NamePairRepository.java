package de.codereddev.architecture.model;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Random;

import de.codereddev.architecture.MainContract;

/**
 * This will simulate a data repository that could retrieve data
 * from local or network providers in an actual implementation.
 *
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

    private ArrayList<MainContract.Observer> observers = new ArrayList<>();

    private NamePair namePair;

    private Random random = new Random();

    private NamePairRepository() {
        namePair = new NamePair(firstNames[0], lastNames[0]);
    }

    public void addObserver(MainContract.Observer observer) {
        observers.add(observer);
    }

    public void deleteObserver(MainContract.Observer observer) {
        observers.remove(observer);
    }

    public NamePair getNamePair() {
        return namePair;
    }

    public void updateFirstName() {
        namePair.setFirstName(firstNames[random.nextInt(firstNames.length)]);
        // This simulates a data request
        new Handler().postDelayed(this::notifyObservers, 1000);
    }

    public void updateLastName() {
        namePair.setLastName(lastNames[random.nextInt(lastNames.length)]);
        // This simulates a data request
        new Handler().postDelayed(this::notifyObservers, 1000);
    }

    private void notifyObservers() {
        for (MainContract.Observer observer: observers) {
            observer.onModelUpdated();
        }
    }
}
