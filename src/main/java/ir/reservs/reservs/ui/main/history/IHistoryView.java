package ir.reservs.reservs.ui.main.history;

import java.util.List;

import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.ui.base.IView;

public interface IHistoryView extends IView {
    void setHistoryData(List<ReserveHistory> reserves);
}
