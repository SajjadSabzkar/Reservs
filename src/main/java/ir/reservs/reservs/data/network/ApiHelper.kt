package ir.reservs.reservs.data.network

import io.reactivex.Single
import ir.reservs.reservs.model.ChangePassword
import ir.reservs.reservs.model.ReserveHistory
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Success
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.model.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiHelper {
    @POST("auth/register")
    @FormUrlEncoded
    fun register(@Field("name") name: String,
                 @Field("phone_number") phone: String,
                 @Field("password") password: String): Single<User>


    @POST("auth/login")
    @FormUrlEncoded
    fun login(@Field("phone") phone: String, @Field("password") password: String): Single<User>

    @GET("reserves")
    fun reserves(): Single<List<ReserveHistory>>

    @POST("reserve")
    @FormUrlEncoded
    fun reserve(@Field("time_id") time_id: String,
                @Field("salon_id") salon_id: Int?,
                @Field("authority") authority: String,
                @Field("date") date: String): Single<Success>

    @POST("reserve/update")
    @FormUrlEncoded
    fun reserveUpdate(@Field("authority") authority: String): Single<Success>

    @GET("salons")
    fun salons(): Single<List<Salon>>

    @PUT("info")
    @FormUrlEncoded
    fun updateName(@Field("name") name: String): Single<Success>


    @PUT("password")
    @FormUrlEncoded
    fun updatePassword(@Field("current_password") current_password: String,
                       @Field("new_password") new_password: String): Single<ChangePassword>

    @GET("times/{salon_id}/{date}")
    fun times(@Path("salon_id") salon_id: Int, @Path("date") date: String): Single<List<Time>>

}