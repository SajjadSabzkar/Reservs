package ir.reservs.reservs.ui.main.settings;

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.login.LoginRegisterActivity
import kotlinx.android.synthetic.main.layout_settings.*
import javax.inject.Inject


class SettingsFragment : BaseFragment(), SettingsContract.View {

    var settingsPresenter: SettingsPresenter? = null
        @Inject set

    var dialog: AlertDialog? = null
        @Inject set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_settings, container, false)
    }

    override fun setUserInfo(name: String, phone: String) {
        txtName.text = name
        txtPhone.text = phone
    }

    override fun setUserImage(image: String) {
        Picasso.get().load(image).fit().resize(128, 128).into(imgAvatar)
    }

    override fun openLoginActivity() {
        startActivity(Intent(activity, LoginRegisterActivity::class.java))
        activity?.finish()
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        settingsPresenter?.onAttach(this)
        settingsPresenter?.setUserProfileData()
        cardAboutMe.setOnClickListener { findNavController().navigate(R.id.settingsToAbout) }
        cardLogout.setOnClickListener { settingsPresenter?.logoutUser() }
        cardEdit.setOnClickListener { findNavController().navigate(R.id.settingsToInformation) }
        imgAvatar.setOnClickListener { settingsPresenter?.pickImage(fragmentManager!!, context!!) }
        passwordCard.setOnClickListener { findNavController().navigate(R.id.settingsToPassword) }
    }

    override fun showProgress() {
        dialog?.show()
    }

    override fun hideProgress() {
        dialog?.hide()
    }

    override fun onDestroyView() {
        if (settingsPresenter != null) {
            settingsPresenter?.onDetach()
            settingsPresenter = null
        }
        if (dialog != null) {
            dialog?.dismiss()
            dialog = null
        }
        super.onDestroyView()
    }
}
