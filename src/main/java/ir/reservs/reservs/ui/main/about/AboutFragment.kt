package ir.reservs.reservs.ui.main.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment() {
    override fun setup(view: View) {
        btnGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/rfmhb2/reservs")
            startActivity(intent)
        }
        btnInstagram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://instagram.com/reservs.ir")
            startActivity(intent)
        }
        btnTelegram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://t.me/rfmhb")
            startActivity(intent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}