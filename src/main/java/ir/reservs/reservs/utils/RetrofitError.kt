package ir.reservs.reservs.utils

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import ir.reservs.reservs.ReserveApplication
import ir.reservs.reservs.model.ErrorMessage
import ir.reservs.reservs.ui.base.BaseFragmentContract
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

object RetrofitError {
    fun handle(view: BaseFragmentContract.View, error: Throwable) {
        try {
            val httpError = error as HttpException
            when (httpError.code()) {
                404 -> view.onError("یافت نشد")
                403 -> view.onTokenExpire()
                401 -> view.onTokenExpire()
                500 -> view.onError("خطا از سمت سرور لطفا بعدا تلاش کنید")
                400 -> {
                    try {
                        val errorMessage = getErrorBodyAs(ErrorMessage::class.java, httpError)
                        view.onError(errorMessage?.error!!)
                    } catch (error: Exception) {
                        view.onError(httpError.message())
                    }
                }
                //422 -> view.onError("مقادیر ورودی نامعتبر")
                else -> view.onError("خطای ناشناخته کد:" + httpError.code())
            }
        } catch (error: Throwable) {
            view.onError("خطا در اتصال به اینترنت")
            view.errorState()
        }
    }

    fun code(error: Throwable): Int {
        return try {
            val httpError: HttpException = error as HttpException
            httpError.code();
        } catch (error: Throwable) {
            -1
        }
    }

    @Throws(IOException::class)
    fun <T> getErrorBodyAs(type: Class<T>, error: HttpException): T? {
        val converter: Converter<ResponseBody, T> = ReserveApplication.getComponent().retrofit()
                .responseBodyConverter<T>(type, arrayOfNulls<Annotation>(0))
        return converter.convert(error.response().errorBody()!!)
    }
}