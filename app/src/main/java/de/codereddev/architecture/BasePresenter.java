package de.codereddev.architecture;

public interface BasePresenter {
    /**
     * Is called by the View to notify the Presenter that it is ready to be updated.
     */
    void subscribe();

    /**
     * Is called by the View to notify the Presenter that
     * it is no longer interested in being updated.
     */
    void unsubscribe();
}
