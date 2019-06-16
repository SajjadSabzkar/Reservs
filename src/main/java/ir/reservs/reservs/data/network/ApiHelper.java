package ir.reservs.reservs.data.network;

import java.util.List;

import io.reactivex.Single;
import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.model.Salon;
import ir.reservs.reservs.model.Login;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiHelper {

    @POST("mobile/auth/login")
    @FormUrlEncoded
    Single<Login> login(@Field("phone") String phone, @Field("password") String password);

    @GET("mobile/reserves")
    Single<List<ReserveHistory>> reserves();

    @GET("mobile/salons")
    Single<List<Salon>> salons();
}