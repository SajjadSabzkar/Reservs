package ir.reservs.reservs.ui.main.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment

class AboutFragment : BaseFragment() {
    override fun setup(view: View?) {
        view?.findViewById<Button>(R.id.btnGithub)?.setOnClickListener {
            Toast.makeText(context, "Github", Toast.LENGTH_LONG).show()
        }
        view?.findViewById<Button>(R.id.btnInstagram)?.setOnClickListener {
            Toast.makeText(context, "Instagram", Toast.LENGTH_LONG).show()
        }
        view?.findViewById<Button>(R.id.btnTelegram)?.setOnClickListener {
            Toast.makeText(context, "Telegram", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}