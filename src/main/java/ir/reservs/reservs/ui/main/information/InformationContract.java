
package ir.reservs.reservs.ui.main.information;

import ir.reservs.reservs.ui.base.BaseFragmentContract;

public class InformationContract {
    interface View extends BaseFragmentContract.View {
        void nameUpdated(String name);

        void initializeViews(String name);
    }

    interface Presenter extends BaseFragmentContract.Presenter<View> {
        void confirmInformation(String name);

        void initializeViews();
    }
}