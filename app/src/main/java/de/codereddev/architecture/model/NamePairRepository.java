package de.codereddev.architecture.model;

import java.util.Random;

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

    private NamePair namePair;

    private Random random = new Random();

    private NamePairRepository() {
        namePair = new NamePair(firstNames[0], lastNames[0]);
    }

    public NamePair getNamePair() {
        return namePair;
    }

    public void updateFirstName() {
        namePair.setFirstName(firstNames[random.nextInt(firstNames.length)]);
    }

    public void updateLastName() {
        namePair.setLastName(lastNames[random.nextInt(lastNames.length)]);
    }
}
