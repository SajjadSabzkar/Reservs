package ir.reservs.reservs.data.network;

import java.util.List;

import io.reactivex.Single;
import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.model.Token;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiHelper {

    @POST("mobile/auth/login")
    @FormUrlEncoded
    Single<Token> login(@Field("phone") String phone, @Field("password") String password);

    @GET("mobile/reserves")
    Single<List<ReserveHistory>> reserves();
}