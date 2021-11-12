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
import ir.reservs.reservs.utils.TimeUtils.timestampToPersian
import okhttp3.MultipartBody
import java.io.File
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody

class SettingsPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : SettingsContract.Presenter {

    private var view: SettingsContract.View? = null

    override fun onAttach(view: SettingsContract.View) {
        this.view = view
    }

    override fun logoutUser() {
        dataManager.logout()
        view?.openLoginPage()
    }

    fun setUserProfileData() {
        Log.e("birthday", dataManager.getUserBirthday() + "")
        val imageUrl = dataManager.getCurrentUserImage()
        if (imageUrl != "-1") {
            view?.setUserImage(imageUrl)
        }
        view?.setUserInfo(dataManager.getCurrentUserName(), dataManager.getCurrentUserPhone(),
                dataManager.getCredit())
    }

    fun pickImage(fragmentManager: FragmentManager, context: Context) {
        val d = RxImagePicker.with(fragmentManager).requestImage(Sources.GALLERY)
                .flatMap {
                    RxImageConverters.uriToFile(context, it, createTempFile(context))
                }
                .subscribe({ it ->
                    if (it.length() / 1024 / 1024 > 2) {
                        view?.onError("حجم عکس انتخابی نباید بیشتر از ۲ مگابایت باشد.")
                        return@subscribe
                    }
                    val requestBodyImage = it.asRequestBody("multipart/form-data".toMediaType())
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