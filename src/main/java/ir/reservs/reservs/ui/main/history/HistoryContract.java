package ir.reservs.reservs.ui.main.history;

import java.util.List;

import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.ui.base.BaseFragmentContract;

public class HistoryContract {

    public interface View extends BaseFragmentContract.View {
        void setHistoryData(List<ReserveHistory> reserves);

        void loadingState();

        void normalState();

        void errorState();

        void emptyState();
    }

    public interface Presenter extends BaseFragmentContract.Presenter<View> {
        void getDataHistory();
    }


}