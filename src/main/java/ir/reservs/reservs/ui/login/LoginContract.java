package ir.reservs.reservs.ui.login;

public class LoginContract {
    interface View {
        void openMainActivity();
    }

    interface presenter {
        void login(String phone, String password);
    }
}
