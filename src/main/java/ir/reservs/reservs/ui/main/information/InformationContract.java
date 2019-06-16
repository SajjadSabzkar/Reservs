package ir.reservs.reservs.ui.main.information;

import ir.reservs.reservs.ui.base.BasePresenter;
import ir.reservs.reservs.ui.base.BaseView;

public class InformationContract {
    interface View extends BaseView {
        void nameUpdated(String name);
    }

    interface Presenter extends BasePresenter<View> {
        void confirmInformation(String name);
    }
}
