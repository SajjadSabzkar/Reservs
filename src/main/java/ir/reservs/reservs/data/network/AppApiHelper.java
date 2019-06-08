package ir.reservs.reservs.data.network;

import java.util.List;

import io.reactivex.Single;
import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.model.Token;

public class AppApiHelper implements ApiHelper {
    @Override
    public Single<Token> login(String phone, String password) {
        ServiceGenerator serviceGenerator = new ServiceGenerator("");
        return serviceGenerator.getApiService().login(phone, password);
    }

    @Override
    public Single<List<ReserveHistory>> reserves() {
        return null;
    }
}
