package de.codereddev.architecture;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import de.codereddev.architecture.model.NamePair;
import de.codereddev.architecture.model.NamePairRepository;

public class PairViewModel extends ViewModel {

    private final NamePairRepository repository = NamePairRepository.getInstance();

    private LiveData<NamePair> liveNamePair;

    private LiveData<NamePair> getLiveNamePair() {
        if (liveNamePair == null) {
            liveNamePair = repository.getNamePair();
        }
        return liveNamePair;
    }

    public LiveData<String> getFirstName() {
        return Transformations.map(getLiveNamePair(), NamePair::getFirstName);
    }

    public LiveData<String> getLastName() {
        return Transformations.map(getLiveNamePair(), NamePair::getLastName);
    }

    public void updateFirstName() {
        repository.updateFirstName();
    }

    public void updateLastName() {
        repository.updateLastName();
    }
}
