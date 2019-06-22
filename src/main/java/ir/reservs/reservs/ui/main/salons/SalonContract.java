package ir.reservs.reservs.ui.main.salons;

import java.util.List;

import ir.reservs.reservs.model.Salon;
import ir.reservs.reservs.ui.base.BaseContract;

public class SalonContract {

    interface View extends BaseContract.View {
        void setSalonsData(List<Salon> salons);
    }

    interface Presenter extends BaseContract.Presenter<View> {
    }
}
