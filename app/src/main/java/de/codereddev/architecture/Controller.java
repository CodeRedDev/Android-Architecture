package de.codereddev.architecture;

import de.codereddev.architecture.model.NamePairRepository;

public class Controller implements MainContract.Observer {

    private NamePairRepository repository = NamePairRepository.getInstance();

    private MainContract.View namePairView;

    public Controller(MainContract.View namePairView) {
        this.namePairView = namePairView;
    }

    public void updateFirstName() {
        namePairView.showProgressBar();
        repository.updateFirstName();
    }

    public void updateLastName() {
        namePairView.showProgressBar();
        repository.updateLastName();
    }

    @Override
    public void onModelUpdated() {
        namePairView.hideProgressBar();
    }
}
