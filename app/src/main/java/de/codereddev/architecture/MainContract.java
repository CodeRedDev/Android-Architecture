package de.codereddev.architecture;

public class MainContract {

    public interface View {
        void showProgressBar();

        void hideProgressBar();
    }

    public interface Observer {
        void onModelUpdated();
    }
}
