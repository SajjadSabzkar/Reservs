package ir.reservs.reservs.ui.base;

public class BaseContract {
    public interface Presenter<T> {
        void onAttach(T view);

        void onDetach();
    }

    public interface View {
        void onError(String msg);

        void onError(int resId);

    }
}
