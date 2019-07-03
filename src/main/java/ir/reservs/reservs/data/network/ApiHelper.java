package ir.reservs.reservs.data.network;

import java.util.List;

import io.reactivex.Single;
import ir.reservs.reservs.model.ChangePassword;
import ir.reservs.reservs.model.User;
import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.model.Salon;
import ir.reservs.reservs.model.Success;
import ir.reservs.reservs.model.Time;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiHelper {
    @POST("auth/register")
    @FormUrlEncoded
    Single<User> register(@Field("name") String name,
                          @Field("phone") String phone,
                          @Field("password") String password);


    @POST("auth/login")
    @FormUrlEncoded
    Single<User> login(@Field("phone") String phone, @Field("password") String password);

    @GET("reserves")
    Single<List<ReserveHistory>> reserves();

    @GET("salons")
    Single<List<Salon>> salons();

    @PUT("info")
    @FormUrlEncoded
    Single<Success> updateName(@Field("name") String name);


    @PUT("password")
    @FormUrlEncoded
    Single<ChangePassword> updatePassword(@Field("current_password") String current_password,
                                          @Field("new_password") String new_password);

    @GET("times/{salon_id}/{date}")
    Single<List<Time>> times(@Path("salon_id") int salon_id, @Path("date") String date);

}