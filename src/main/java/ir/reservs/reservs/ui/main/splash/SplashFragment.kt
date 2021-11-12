package ir.reservs.reservs.ui.main.splash

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.Settings.Secure
import android.telephony.TelephonyManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.iid.FirebaseInstanceId
import ir.reservs.reservs.BuildConfig
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.main.MainActivity
import kotlinx.android.synthetic.main.layout_splash.*
import javax.inject.Inject


class SplashFragment : BaseFragment(), SplashContract.View {
    var splashPresenter: SplashPresenter? = null
        @Inject set

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        (activity as MainActivity).hideBottomNavigation()
        splashPresenter?.onAttach(this)
        sendDeviceInformation()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_splash_new, container, false)
    }

    override fun onDestroyView() {
        if (splashPresenter != null) {
            splashPresenter?.onDetach()
            splashPresenter = null
        }
        super.onDestroyView()
    }

    override fun goToAuthentication() {
        findNavController().navigate(R.id.action_splashFragment_to_sendFragment)
    }

    override fun goToHome() {
        findNavController().navigate(R.id.action_splashFragment_to_historyFragment)
    }

    private fun sendDeviceInformation() {
        val androidId = Secure.getString(requireContext().contentResolver, Secure.ANDROID_ID)
        val versionCode = BuildConfig.VERSION_CODE.toString()
        val manager = requireContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val operator: String = manager.networkOperatorName
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        val versionRelease = Build.VERSION.RELEASE
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.e("fcmToken", it.token)
            splashPresenter?.sendDeviceInfo(androidId, it.token, versionCode, versionRelease, operator, manufacturer, model)
        }.addOnFailureListener {
            splashPresenter?.sendDeviceInfo(androidId, null, versionCode, versionRelease, operator, model, manufacturer)
        }
    }

    override fun internetNotAvailable() {
        val mySnackbar = Snackbar.make(constraintLayoutSplash, "اتصال اینترنت خود را بررسی کنید", Snackbar.LENGTH_INDEFINITE)
        mySnackbar.setAction("تلاش مجدد") {
            sendDeviceInformation()
        }
        mySnackbar.show()
    }
}