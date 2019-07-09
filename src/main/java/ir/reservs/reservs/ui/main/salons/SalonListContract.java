package ir.reservs.reservs.ui.main.salons;

import java.util.List;

import ir.reservs.reservs.model.Salon;
import ir.reservs.reservs.ui.base.BaseFragmentContract;

public class SalonListContract {

    interface View extends BaseFragmentContract.View {
        void setSalonsData(List<Salon> salons);

        void errorState();

        void emptyState();
    }

    interface Presenter extends BaseFragmentContract.Presenter<View> {
    }
}
