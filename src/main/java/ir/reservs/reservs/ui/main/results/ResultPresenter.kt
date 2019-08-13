package ir.reservs.reservs.ui.main.results;


class ResultPresenter() : ResultContract.Presenter {

    private var view: ResultContract.View? = null

    override fun onAttach(view: ResultContract.View) {
        this.view = view
    }

    fun onReceive(status: String) {
        view?.initializeViews(status.equals("OK"))
    }


    override fun onDetach() {
        view = null
    }

}
