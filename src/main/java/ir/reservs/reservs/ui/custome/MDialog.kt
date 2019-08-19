package ir.reservs.reservs.ui.custome

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AlertDialog

import java.util.Objects

import ir.reservs.reservs.R

class MDialog private constructor(private val context: Context) {
    private val txtTitle: TextView
    private val txtDescription: TextView
    private val btn: Button
    private val img: ImageView
    private val mView: View

    enum class STATE {
        Success, Failed
    }

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mView = inflater.inflate(R.layout.dialog_base, null)
        txtTitle = mView.findViewById(R.id.txtTitle)
        txtDescription = mView.findViewById(R.id.txtDescription)
        btn = mView.findViewById(R.id.btnAction)
        img = mView.findViewById(R.id.imgState)
    }

    private fun setTitleText(str: String) {
        txtTitle.text = str
    }

    private fun setDescriptionText(str: String) {
        txtDescription.text = str
    }

    private fun setButtonText(str: String) {
        btn.text = str
    }

    private fun setButtonBackgroundColor(color: Int) {
        btn.setBackgroundColor(color)
    }

    private fun setButtonBackgroundResId(resId: Int) {
        btn.setBackgroundResource(resId)
    }

    private fun setButtonTextColor(color: Int) {
        btn.setTextColor(color)
    }

    private fun setState(state: STATE) {
        if (state == STATE.Success) {
            img.setImageResource(R.drawable.ic_success)
        } else {
            img.setImageResource(R.drawable.ic_failed)
        }
    }

    fun show() {
        mView.invalidate()
        val materialDialogs = AlertDialog.Builder(context)
                .setView(mView)
                .create()
        Objects.requireNonNull<Window>(materialDialogs.window).setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        materialDialogs.show()
    }

    class MDialogBuilder {
        private var title: String=""
        private var description: String=""
        private var btnText: String=""
        private var background: Int = 0
        private var resId: Int = 0
        private var textColor: Int = 0
        private var state: MDialog.STATE=STATE.Success

        fun title(title: String): MDialogBuilder {
            this.title = title
            return this
        }

        fun description(description: String): MDialogBuilder {
            this.description = description
            return this
        }

        fun btnText(btnText: String): MDialogBuilder {
            this.btnText = btnText
            return this
        }

        fun buttonBackground(background: Int): MDialogBuilder {
            this.background = background
            return this
        }

        fun buttonBackgroundResId(resId: Int): MDialogBuilder {
            this.resId = resId
            return this
        }

        fun buttonTextColor(textColor: Int): MDialogBuilder {
            this.textColor = textColor
            return this
        }

        fun state(state: STATE): MDialogBuilder {
            this.state = state
            return this
        }

        fun build(context: Context): MDialog {
            val mDialog = MDialog(context)
            mDialog.setDescriptionText(description)
            mDialog.setButtonText(btnText)
            if (background != 0) {
                mDialog.setButtonBackgroundColor(background)
            } else {
                mDialog.setButtonBackgroundResId(resId)
            }
            mDialog.setButtonTextColor(textColor)
            mDialog.setState(state)
            return mDialog
        }
    }
}
