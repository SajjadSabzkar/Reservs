package ir.reservs.reservs.ui.main.password;

import ir.reservs.reservs.ui.base.BaseContract;

public class PasswordContract {
  interface View extends BaseContract.View {
    void passwordChangedSuccessful();
  }

  interface Presenter extends BaseContract.Presenter<View> {
    void changePassword(String current_password, String new_password);
  }
}