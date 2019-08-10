package ir.reservs.reservs.ui.main.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import kotlinx.android.synthetic.main.layout_informaion.*
import javax.inject.Inject

class InformationFragment : BaseFragment(), InformationContract.View {

    var informationPresenter: InformationPresenter? = null
        @Inject set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_informaion, container, false)
    }


    override fun onError(msg: String) {
        Snackbar.make(txtName, msg, Snackbar.LENGTH_LONG).show()
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        informationPresenter!!.onAttach(this)
        btnConfirm.setOnClickListener { informationPresenter!!.confirmInformation(txtName!!.text.toString()) }
        informationPresenter!!.initializeViews()
    }

    override fun onError(resId: Int) {
        onError(getString(resId))
    }

    override fun nameUpdated(name: String) {
        onError(String.format("نام شما به %s تغییر کرد.", name))
        NavHostFragment.findNavController(this).popBackStack()
    }

    override fun initializeViews(name: String) {
        txtName!!.setText(name)
    }

    override fun onDestroy() {
        super.onDestroy()
        informationPresenter!!.onDetach()
    }
}