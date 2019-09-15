package de.codereddev.architecture;

import android.os.Handler;

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
        namePairView.showProgressBar();
        repository.updateFirstName();

        if (isSubscribed) {new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NamePair currentPair = repository.getNamePair();
                namePairView.hideProgressBar();
                namePairView.showFirstName(currentPair.getFirstName());
            }
        }, 1000);
        }
    }

    @Override
    public void updateLastName() {
        namePairView.showProgressBar();
        repository.updateLastName();

        if (isSubscribed) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    NamePair currentPair = repository.getNamePair();
                    namePairView.hideProgressBar();
                    namePairView.showLastName(currentPair.getLastName());
                }
            }, 1000);
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
