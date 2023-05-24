package com.example.smarthome.ui.fragment_home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smarthome.R

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState == null) {
            childFragmentManager.beginTransaction()?.apply {
                replace(R.id.fragment_home_frame, HomeDevicesFragment.newInstance())
                commit()
            }
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {

            }
    }
}