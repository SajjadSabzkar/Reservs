package ir.reservs.reservs.ui.base;


public interface IPresenter<V extends IView> {
    void onAttach(V view);

    void onDetach();
}
