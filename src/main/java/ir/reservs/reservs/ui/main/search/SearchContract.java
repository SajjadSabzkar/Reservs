package ir.reservs.reservs.ui.main.search;

import ir.reservs.reservs.ui.base.BasePresenter;
import ir.reservs.reservs.ui.base.BaseView;

public class SearchContract {
    interface View extends BaseView {
        void setDate(int year, int month, int day);

        void setCity(String city);

        void setLocation(String location);
    }

    interface Presenter extends BasePresenter<View> {
        void getDate();

        void getCity();

        void getLocation();
    }
}
