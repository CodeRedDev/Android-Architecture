package de.codereddev.architecture;

import de.codereddev.architecture.model.NamePairRepository;

public class Controller {

    private NamePairRepository repository = NamePairRepository.getInstance();

    private MainContract.View namePairView;

    public Controller(MainContract.View namePairView) {
        this.namePairView = namePairView;
    }

    public void updateFirstName() {
        repository.updateFirstName();
    }

    public void updateLastName() {
        repository.updateLastName();
    }
}
