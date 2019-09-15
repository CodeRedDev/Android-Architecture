package de.codereddev.architecture;

import de.codereddev.architecture.model.NamePair;
import de.codereddev.architecture.model.NamePairRepository;

public class NamePairPresenter implements MainContract.Presenter {

    private NamePairRepository repository = NamePairRepository.getInstance();

    private MainContract.View namePairView;

    private boolean isSubscribed = false;

    public NamePairPresenter(MainContract.View namePairView) {
        this.namePairView = namePairView;
    }

    @Override
    public void loadNamePair() {
        NamePair currentPair = repository.getNamePair();

        if (isSubscribed) {
            namePairView.showFirstName(currentPair.getFirstName());
            namePairView.showLastName(currentPair.getLastName());
        }
    }

    @Override
    public void updateFirstName() {
        repository.updateFirstName();

        NamePair currentPair = repository.getNamePair();

        if (isSubscribed) {
            namePairView.showFirstName(currentPair.getFirstName());
        }
    }

    @Override
    public void updateLastName() {
        repository.updateLastName();

        NamePair currentPair = repository.getNamePair();

        if (isSubscribed) {
            namePairView.showLastName(currentPair.getLastName());
        }
    }

    @Override
    public void subscribe() {
        isSubscribed = true;
        loadNamePair();
    }

    @Override
    public void unsubscribe() {
        isSubscribed = false;
    }
}
