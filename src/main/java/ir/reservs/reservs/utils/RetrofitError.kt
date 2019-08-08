package ir.reservs.reservs.utils

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import ir.reservs.reservs.ui.base.BaseFragmentContract

object RetrofitError {
    fun handle(view: BaseFragmentContract.View, error: Throwable) {
        try {
            val httpError: HttpException = error as HttpException
            when (httpError.code()) {
                404 -> view.onError("یافت نشد")
                403 -> view.onTokenExpire()
                401 -> view.onTokenExpire()
                500 -> view.onError("خطا از سمت سرور لطفا بعدا تلاش کنید")
                400 -> view.onError(httpError.message())
                //422 -> view.onError("مقادیر ورودی نامعتبر")
                else -> view.onError("خطای ناشناخته کد:" + httpError.code())
            }
        } catch (error: Throwable) {
            view.onError("خطا در اتصال به اینترنت")
            view.errorState()
        }
    }

    fun code(error: Throwable): Int {
        try {
            val httpError: HttpException = error as HttpException
            return httpError.code();
        } catch (error: Throwable) {
            return -1
        }
    }
}