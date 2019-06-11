package ir.reservs.reservs.ui.main.history;

import java.util.List;

import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.ui.base.BasePresenter;
import ir.reservs.reservs.ui.base.BaseView;

public class HistoryContract {
    public interface Presenter extends BasePresenter<HistoryContract.View> {
        void getDataHistory();
    }

    public interface View extends BaseView {
        void setHistoryData(List<ReserveHistory> reserves);
    }
}
