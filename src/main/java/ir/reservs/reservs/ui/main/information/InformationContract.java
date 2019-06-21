package ir.reservs.reservs.ui.main.information;

import ir.reservs.reservs.ui.base.BaseContract;

public class InformationContract {
    interface View extends BaseContract.View {
        void nameUpdated(String name);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void confirmInformation(String name);
    }
}
