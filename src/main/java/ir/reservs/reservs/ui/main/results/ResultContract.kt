package ir.reservs.reservs.ui.main.results;

import android.content.Context
import android.net.Uri
import ir.reservs.reservs.ui.base.BaseContract;

class ResultContract {
    interface View : BaseContract.View {
        fun showProgress()
        fun hideProgress()
        fun initializeViews(state: Boolean)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onReceive(context: Context, data: Uri)
    }
}