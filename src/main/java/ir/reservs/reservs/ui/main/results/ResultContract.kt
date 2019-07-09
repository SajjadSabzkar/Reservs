package ir.reservs.reservs.ui.main.results;

import android.content.Context
import android.net.Uri
import ir.reservs.reservs.ui.base.BaseFragmentContract;

class ResultContract {
    interface View : BaseFragmentContract.View {
        fun showProgress()
        fun hideProgress()
        fun initializeViews(state: Boolean)
    }

    interface Presenter : BaseFragmentContract.Presenter<View> {
        fun onReceive(context: Context, data: Uri)
    }
}