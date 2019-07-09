package ir.reservs.reservs.ui.main.password;

import ir.reservs.reservs.ui.base.BaseFragmentContract;

public class PasswordContract {
  interface View extends BaseFragmentContract.View {
    void passwordChangedSuccessful();
  }

  interface Presenter extends BaseFragmentContract.Presenter<View> {
    void changePassword(String current_password, String new_password);
  }
}