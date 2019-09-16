package de.codereddev.architecture;

import de.codereddev.architecture.model.NamePair;
import de.codereddev.architecture.model.NamePairRepository;
import io.reactivex.rxjava3.core.Observable;

public class ViewModel {

    private final NamePairRepository repository = NamePairRepository.getInstance();

    public Observable<NamePair> getNamePair() {
        return repository.getNamePair();
    }

    public void updateFirstName() {
        repository.updateFirstName();
    }

    public void updateLastName() {
        repository.updateLastName();
    }
}
