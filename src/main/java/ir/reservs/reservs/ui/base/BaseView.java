package ir.reservs.reservs.ui.base;

public interface BaseView {
    void onError(String msg);

    void onError(int resId);

    void setup();
}
