package ir.reservs.reservs.ui.custome;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.Objects;

import ir.reservs.reservs.R;

public class MDialog {
    private TextView txtTitle;
    private TextView txtDescription;
    private Button btn;
    private ImageView img;
    private View mView;
    private Context context;

    public enum STATE {
        Success, Failed
    }

    private MDialog(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.dialog_base, null);
        init();
    }

    private void init() {
        txtTitle = mView.findViewById(R.id.txtTitle);
        txtDescription = mView.findViewById(R.id.txtDescription);
        btn = mView.findViewById(R.id.btn);
        img = mView.findViewById(R.id.img);
    }


    private void setTitleText(String str) {
        Log.e("MDialog", "setTitleText");
        txtTitle.setText(str);
        Log.e("MDialog", "setTitleText" + ": " + 2);
    }

    private void setDescriptionText(String str) {
        txtDescription.setText(str);
    }

    private void setButtonText(String str) {
        btn.setText(str);
    }

    private void setButtonBackgroundColor(int color) {
        btn.setBackgroundColor(color);
    }

    private void setButtonBackgroundResId(int resId) {
        btn.setBackgroundResource(resId);
    }

    private void setButtonTextColor(int color) {
        btn.setTextColor(color);
    }

    private void setState(STATE state) {
        if (state == STATE.Success) {
            img.setImageResource(R.drawable.ic_success);
        } else {
            img.setImageResource(R.drawable.ic_failed);
        }
    }

    public void show() {
        mView.invalidate();

        AlertDialog materialDialogs = new AlertDialog.Builder(context)
                .setView(mView)
                .create();
        Objects.requireNonNull(materialDialogs.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        materialDialogs.show();
    }

    public static class MDialogBuilder {
        String title;
        String description;
        String btnText;
        int background;
        int resId;
        int textColor;
        MDialog.STATE state;

        public MDialogBuilder title(String title) {
            this.title = title;
            return this;
        }

        public MDialogBuilder description(String description) {
            this.description = description;
            return this;
        }

        public MDialogBuilder btnText(String btnText) {
            this.btnText = btnText;
            return this;
        }

        public MDialogBuilder buttonBackground(int background) {
            this.background = background;
            return this;
        }

        public MDialogBuilder buttonBackgroundResId(int resId) {
            this.resId = resId;
            return this;
        }

        public MDialogBuilder buttonTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public MDialogBuilder state(STATE state) {
            this.state = state;
            return this;
        }

        public MDialog build(Context context) {
            Log.e("MDialogBuilder", "build");
            MDialog mDialog = new MDialog(context);
            mDialog.setDescriptionText(description);
            mDialog.setButtonText(btnText);
            if (background != 0) {
                mDialog.setButtonBackgroundColor(background);
            } else {
                mDialog.setButtonBackgroundResId(resId);
            }
            mDialog.setButtonTextColor(textColor);
            mDialog.setState(state);
            return mDialog;
        }
    }
}
