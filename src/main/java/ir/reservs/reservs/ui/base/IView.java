package ir.reservs.reservs.ui.base;

public interface IView {
    void onError(String error);

    void onError(long resId);

    void init();
}
