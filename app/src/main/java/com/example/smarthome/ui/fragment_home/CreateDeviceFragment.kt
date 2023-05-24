package com.example.smarthome.ui.fragment_home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.smarthome.R
import com.example.smarthome.httpclient.ApiService


class CreateDeviceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var position: Int? = null
        var device_name: String? = null
        var device_place: String? = null

        var spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            arrayOf("Лампочка", "Камера", "Датчик")
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = view.findViewById<Spinner>(R.id.choose_device)
        val deviceName = view.findViewById<EditText>(R.id.edit_device_name)
        val devicePlace = view.findViewById<EditText>(R.id.edit_device_place)

        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                position = p2
            }
        }

        val button = view.findViewById<Button>(R.id.create_device_button)
        button.setOnClickListener {
            device_name = deviceName.text.toString()
            device_place = devicePlace.text.toString()
            position = spinner.selectedItemPosition
            if (device_name == null)  {
                Toast.makeText(context, "Введите название устройства!", Toast.LENGTH_SHORT).show()
            } else if (device_place == null) {
                Toast.makeText(context, "Введите расположение устройства!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                 when(position){
                     0 -> ApiService().createLight(context, device_name, device_place)
                     1 -> ApiService().createCamera(context, device_name, device_place)
                     2 -> ApiService().createDetector(context, device_name, device_place)
                 }
                parentFragmentManager.beginTransaction().replace(R.id.fragment_home_frame, HomeFragment.newInstance()).commit()
            }
        }

    }

    @SuppressLint("ResourceType")
    override fun onStart() {
        super.onStart()
    }


    companion object {
        fun newInstance() = CreateDeviceFragment()
    }
}