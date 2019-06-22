package ir.reservs.reservs.ui.main.search;

import ir.reservs.reservs.ui.base.BaseContract;

public class SearchContract {
    interface View extends BaseContract.View {
        void setDate(int year, int month, int day);

        void setCity(String city);

        void setLocation(String location);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getDate();

        void getCity();

        void getLocation();
    }
}
