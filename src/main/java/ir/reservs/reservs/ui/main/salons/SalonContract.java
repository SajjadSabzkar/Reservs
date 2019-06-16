package ir.reservs.reservs.ui.main.salons;

import java.util.List;

import ir.reservs.reservs.model.Salon;
import ir.reservs.reservs.ui.base.BasePresenter;
import ir.reservs.reservs.ui.base.BaseView;

public class SalonContract {

    interface View extends BaseView {
        void setSalonsData(List<Salon> salons);
    }

    interface Presenter extends BasePresenter<View> {
    }
}
