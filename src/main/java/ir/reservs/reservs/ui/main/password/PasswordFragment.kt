package ir.reservs.reservs.ui.main.password;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import kotlinx.android.synthetic.main.layout_change_password.*
import javax.inject.Inject

class PasswordFragment : BaseFragment(), PasswordContract.View {

    var passwordPresenter: PasswordPresenter? = null
        @Inject set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_change_password, container, false)
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        passwordPresenter?.onAttach(this)
        btnConfirm.setOnClickListener {
            passwordPresenter?.changePassword(
                    txtCurrentPassword.getText().toString(),
                    txtNewPassword.getText().toString()
            )
        }
    }

    override fun passwordChangedSuccessful() {
        onError(getString(R.string.your_password_changed));
        NavHostFragment.findNavController(this).popBackStack();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (passwordPresenter != null) {
            passwordPresenter?.onDetach()
        }

    }
}
