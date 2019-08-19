package ir.reservs.reservs.ui.main.results


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_result.*
import javax.inject.Inject


class ResultFragment : BaseFragment(), ResultContract.View {

    var resultPresenter: ResultPresenter? = null
        @Inject set

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        resultPresenter?.onAttach(this)
        resultPresenter?.onReceive(arguments?.getString("Status")!!)
    }

    override fun initializeViews(state: Boolean) {
        if (state) {
            btnResult.setOnClickListener {
                findNavController().navigate(R.id.resultToHistory)
            }
            return
        }
        imgStatus?.setImageResource(R.drawable.ic_failed)
        txtTitleStatus?.text = getString(R.string.payment_error)
        txtBodyStatus?.text = getString(R.string.return_credit_to_account)
        btnResult?.text = getString(R.string.reservable_locations)
        btnResult?.setOnClickListener {
            findNavController().navigate(R.id.resultToSalons)
        }

    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun hideProgress() {
    }

    override fun showProgress() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resultPresenter?.onDetach()
    }

}
