package ir.reservs.reservs.ui.base;

public interface BasePresenter<T> {
    void onAttach(T view);

    void onDetach();
}
