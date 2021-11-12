package ir.reservs.reservs.ui.base;

class BaseFragmentContract {
    interface View {
        fun onError(msg: String?, type: String = "INFO")
        fun onError(resId: Int)
        fun onTokenExpire()
        fun emptyState()
        fun errorState()
        fun loadingState()
        fun normalState()
    }

    interface Presenter<T> {
        fun onAttach(view: T)
        fun onDetach()
    }
}