package com.lcmobile.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView: TextView = view.findViewById(R.id.text_dashboard)
        val buttonView: Button = view.findViewById(R.id.button_dashboard)

        dashboardViewModel.dataView.observe(viewLifecycleOwner, Observer {
            textView.text = it.text
            buttonView.text = it.button
        })

        buttonView.setOnClickListener {
            dashboardViewModel.openDetail { intent ->
                intent?.let(::startActivity)
            }
        }
    }
}
