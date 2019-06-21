package ir.reservs.reservs.ui.main.history;

import java.util.List;

import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.ui.base.BaseContract;

public class HistoryContract {
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void getDataHistory();
    }

    public interface View extends BaseContract.BaseView {
        void setHistoryData(List<ReserveHistory> reserves);
    }
}
