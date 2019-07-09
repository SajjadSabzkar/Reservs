package ir.reservs.reservs.ui.main.search;

import ir.reservs.reservs.ui.base.BaseFragmentContract;

public class SearchContract {
    interface View extends BaseFragmentContract.View {
        void setDate(int year, int month, int day);

        void setCity(String city);

        void setLocation(String location);
    }

    interface Presenter extends BaseFragmentContract.Presenter<View> {
        void getDate();

        void getCity();

        void getLocation();
    }
}