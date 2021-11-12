package ir.reservs.reservs.ui.main.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.main.MainActivity
import kotlinx.android.synthetic.main.layout_informaion.*
import javax.inject.Inject

class InformationFragment : BaseFragment(), InformationContract.View {

    var informationPresenter: InformationPresenter? = null
        @Inject set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_informaion, container, false)
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        informationPresenter!!.onAttach(this)
        btnConfirm.setOnClickListener {
            informationPresenter!!.confirmInformation(txtName.text.toString())
        }
        cardBirthday.setOnClickListener {
            informationPresenter?.getDate()
        }
        informationPresenter?.initializeViews()
    }

    override fun onError(resId: Int) {
        onError(getString(resId))
    }

    override fun informationUpdated() {
        onError("اطلاعات شما با موفقیت بروز شد.")
        findNavController().navigate(R.id.settingsFragment)
        (activity as MainActivity).showBottomNavigation()
    }

    override fun displayDate(date: String) {
        txtBirthday.text = date
    }

    override fun getFManager(): FragmentManager {
        return childFragmentManager
    }

    override fun initializeViews(name: String, age: String) {
        txtName.setText(name)
        txtBirthday.text = age
    }

    override fun onDestroy() {
        super.onDestroy()
        informationPresenter!!.onDetach()
    }
}