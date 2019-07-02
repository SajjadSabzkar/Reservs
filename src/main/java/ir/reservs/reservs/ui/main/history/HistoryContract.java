package ir.reservs.reservs.ui.main.history;

import java.util.List;

import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.ui.base.BaseContract;

public class HistoryContract {

    public interface View extends BaseContract.View {
        void setHistoryData(List<ReserveHistory> reserves);

        void showProgress();

        void hideProgress();
    }

    public interface Presenter extends BaseContract.Presenter<View> {
        void getDataHistory();
    }


}