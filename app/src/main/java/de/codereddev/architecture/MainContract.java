package de.codereddev.architecture;

public class MainContract {

    public interface View {
        void showFirstName(String firstName);

        void showLastName(String lastName);
    }

    public interface Presenter {
        void loadNamePair();

        void updateFirstName();

        void updateLastName();

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
}
