package de.codereddev.architecture;

import android.os.Handler;

import de.codereddev.architecture.model.NamePair;
import de.codereddev.architecture.model.NamePairRepository;

public class NamePairPresenter implements BasePresenter {

    private NamePairRepository repository = NamePairRepository.getInstance();

    private NamePairView namePairView;

    private boolean isSubscribed = false;

    public NamePairPresenter(NamePairView namePairView) {
        this.namePairView = namePairView;
    }

    public void loadNamePair() {
        NamePair currentPair = repository.getNamePair();

        if (isSubscribed) {
            namePairView.showFirstName(currentPair.getFirstName());
            namePairView.showLastName(currentPair.getLastName());
        }
    }

    public void updateFirstName() {
        namePairView.showProgressBar();
        repository.updateFirstName(() -> {
            if (isSubscribed) {
                NamePair currentPair = repository.getNamePair();
                namePairView.hideProgressBar();
                namePairView.showFirstName(currentPair.getFirstName());
            }
        });
    }

    public void updateLastName() {
        namePairView.showProgressBar();
        repository.updateLastName(() -> {
            if (isSubscribed) {
                NamePair currentPair = repository.getNamePair();
                namePairView.hideProgressBar();
                namePairView.showLastName(currentPair.getLastName());
            }
        });
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
