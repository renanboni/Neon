package com.boni.neon.ui.historic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.boni.neon.NeonApplication
import com.boni.neon.R
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject

class HistoryFragment: Fragment() {

    private lateinit var historicViewModel: HistoryViewModel

    @Inject
    lateinit var factory: HistoryVMFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_history,
        container,
        false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as NeonApplication).createHistoricSubComponent()?.inject(this)
        historicViewModel = ViewModelProviders.of(this, factory).get(HistoryViewModel::class.java)

        historicViewModel.getTransfers()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        historicViewModel.viewState.observe(this, Observer {
            it?.let { handleState(it) }
        })
    }

    private fun handleState(state: HistoryViewState) {
        state.contacts?.let {
            transfers.adapter = HistoryAdapter(it)
        }
    }
}