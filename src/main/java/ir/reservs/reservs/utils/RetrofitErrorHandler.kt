package ir.reservs.reservs.utils

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import ir.reservs.reservs.ui.base.BaseContract

object RetrofitError{
    fun handle(view: BaseContract.View, error: Throwable) {
        try {
            val httpError: HttpException = error as HttpException
            when (httpError.code()) {
                404 -> view.onError("یافت نشد")
                403 -> view.onTokenExpire()
                500 -> view.onError("خطا از سمت سرور لطفا بعدا تلاش کنید")
            }
        } catch (error: Throwable) {
            view.onError("خطا در اتصال به اینترنت")
        }
    }
}