package ir.reservs.reservs.ui.base;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import ir.reservs.reservs.ReserveApplication;
import ir.reservs.reservs.di.component.DaggerFragmentComponent;
import ir.reservs.reservs.di.component.FragmentComponent;
import ir.reservs.reservs.di.module.FragmentModule;

public abstract class BaseFragment extends Fragment implements BaseContract.View {
    private FragmentComponent fragmentComponent;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(ReserveApplication.getComponent()).
                        fragmentModule(new FragmentModule()).build();
        setup(view);
    }

    protected FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }


    public abstract void onError(@NotNull String msg);

    public abstract void setup(View view);

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentComponent = null;
    }

}
