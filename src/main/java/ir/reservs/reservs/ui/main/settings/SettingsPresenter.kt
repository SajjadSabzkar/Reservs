package ir.reservs.reservs.ui.main.settings;

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.mlsdev.rximagepicker.RxImageConverters
import com.mlsdev.rximagepicker.RxImagePicker
import com.mlsdev.rximagepicker.Sources
import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.utils.RetrofitError
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SettingsPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : SettingsContract.Presenter {

    private var view: SettingsContract.View? = null

    override fun onAttach(view: SettingsContract.View) {
        this.view = view
    }

    override fun logoutUser() {
        dataManager.removeAccessToken()
        view?.openLoginActivity()
    }

    fun setUserProfileData() {
        val imageUrl = dataManager.getCurrentUserImage()
        if (imageUrl != "-1") {
            view?.setUserImage(imageUrl)
        }
        view?.setUserInfo(dataManager.getCurrentUserName(),
                dataManager.getCurrentUserPhone())
    }

    fun pickImage(fragmentManager: FragmentManager, context: Context) {
        val d = RxImagePicker.with(fragmentManager).requestImage(Sources.GALLERY)
                .flatMap {
                    RxImageConverters.uriToFile(context, it, createTempFile(context))
                }
                .subscribe({ it ->
                    val requestBodyImage = RequestBody.create(MediaType.parse("multipart/form-data"), it)
                    val part = MultipartBody.Part.createFormData("avatar", it.name, requestBodyImage)
                    view?.showProgress()
                    val d2 = dataManager.updateAvatar(part)
                            .subscribe({
                                view?.hideProgress()
                                dataManager.setCurrentUserImage(it.image_url)
                                view?.setUserImage(it.image_url)
                            }, {
                                view?.hideProgress()
                                RetrofitError.handle(view!!, it)
                            })
                    compositeDisposable.add(d2)
                }, {
                    Log.e("SettingsFragment", "Error: " + it.message)
                })
        compositeDisposable.add(d)
    }

    private fun createTempFile(context: Context): File {
        return File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), System.currentTimeMillis().toString() + "_image.jpeg")
    }

    override fun onDetach() {
        compositeDisposable.clear()
        view = null
    }
}
