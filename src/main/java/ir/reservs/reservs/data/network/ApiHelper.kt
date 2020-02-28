package ir.reservs.reservs.data.network

import io.reactivex.Single
import ir.reservs.reservs.model.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiHelper {
    @POST("auth/register")
    @FormUrlEncoded
    fun register(@Field("name") name: String,
                 @Field("phone_number") phone: String,
                 @Field("password") password: String,
                 @Field("fcmToken") fcmToken: String): Single<User>


    @POST("auth/login")
    @FormUrlEncoded
    fun login(@Field("phone") phone: String,
              @Field("password") password: String,
              @Field("fcmToken") fcmToken: String): Single<User>

    @POST("auth/phone")
    @FormUrlEncoded
    fun sendPhone(@Field("phone") phone: String): Single<Message>

    @POST("auth/confirm")
    @FormUrlEncoded
    fun confirmCode(@Field("phone") phone: String, @Field("code") code: String): Single<User>

    @POST("auth/forget")
    @FormUrlEncoded
    fun forget(@Field("phone_number") phone: String): Single<Success>

    @GET("reserves")
    fun reserves(@Query("page") page: Int): Single<MutableList<History>>

    @POST("reserve")
    @FormUrlEncoded
    fun reserve(@Field("time_id") time_id: String,
                @Field("salon_id") salon_id: Int?,
                @Field("CallbackURL") callBackUrl: String,
                @Field("date") date: String): Single<Payment>

    @POST("reserve/update")
    @FormUrlEncoded
    fun reserveUpdate(@Field("authority") authority: String): Single<Success>

    @GET("salons")
    fun salons(): Single<MutableList<Salon>>

    @GET("city/{city}/salons")
    fun salons(@Path("city") cityId: Int): Single<MutableList<Salon>>

    @PUT("info")
    @FormUrlEncoded
    fun updateName(@Field("name") name: String): Single<Success>

    @PUT("password")
    @FormUrlEncoded
    fun updatePassword(@Field("current_password") current_password: String,
                       @Field("new_password") new_password: String): Single<ChangePassword>

    @GET("times/{salon_id}/{date}")
    fun times(@Path("salon_id") salon_id: Int, @Path("date") date: String): Single<MutableList<Time>>

    @PUT("updateFcmToken")
    @FormUrlEncoded
    fun updateFcmToken(@Field("fcmToken") fcmToken: String): Single<Success>

    @GET("cities")
    fun cities(): Single<MutableList<City>>

    @Multipart
    @POST("avatar")
    fun updateAvatar(@Part avatar: MultipartBody.Part): Single<Avatar>
}