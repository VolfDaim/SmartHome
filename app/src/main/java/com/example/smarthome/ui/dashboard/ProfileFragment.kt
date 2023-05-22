package com.example.smarthome.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smarthome.auth.LoginActivity
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button: Button = view.findViewById(R.id.exit_button)
        val username: TextView = view.findViewById(R.id.username)
        val userplace: TextView = view.findViewById(R.id.userplace)
        val userdevice: TextView = view.findViewById(R.id.userdevices)

        val shPrefs = activity?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)

        username.setText(shPrefs?.getString("username", null))
        userplace.setText(shPrefs?.getString("userplace", null))
        userdevice.setText(shPrefs?.getString("userdevice", null))

        button.setOnClickListener {
            shPrefs?.edit()?.remove("token")?.apply()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}