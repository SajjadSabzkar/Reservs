package ir.reservs.reservs.ui.main.settings;

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import kotlinx.android.synthetic.main.layout_settings_new.*
import kotlinx.android.synthetic.main.layout_settings_new.imgAvatar
import kotlinx.android.synthetic.main.layout_settings_new.txtName
import kotlinx.android.synthetic.main.layout_settings_new.txtPhone
import javax.inject.Inject


class SettingsFragment : BaseFragment(), SettingsContract.View {

    var settingsPresenter: SettingsPresenter? = null
        @Inject set

    var dialog: AlertDialog? = null
        @Inject set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_settings_new, container, false)
    }

    override fun setUserInfo(name: String, phone: String, credit: Long) {
        txtName.text = name
        txtPhone.text = phone
        txtCredit.text = credit.toString()
    }

    override fun setUserImage(image: String) {
        Picasso.get().load(image).resize(128, 128).into(imgAvatar)
    }

    override fun openLoginPage() {
        //todo logout
        findNavController().backStack.clear()
        findNavController().navigate(R.id.sendFragment)
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        settingsPresenter?.onAttach(this)
        settingsPresenter?.setUserProfileData()
        boxContact.setOnClickListener { findNavController().navigate(R.id.settingsToAbout) }
        boxExit.setOnClickListener { settingsPresenter?.logoutUser() }
        boxEdit.setOnClickListener { findNavController().navigate(R.id.settingsToInformation) }
        imgAvatar.setOnClickListener { settingsPresenter?.pickImage(requireActivity().supportFragmentManager, requireContext()) }
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
