package com.example.smarthome.httpclient

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.smarthome.ui.fragment_home.bo.DataModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiService {
    val gson = Gson()
    fun getLights(token: String, editor: SharedPreferences.Editor, view: Context) {
        val getLights = ApiInterface.create().getAllLights(token)

        getLights.enqueue(object :
            Callback<ArrayList<DataModel.Light>> {
            override fun onResponse(
                call: Call<ArrayList<DataModel.Light>>,
                response: Response<ArrayList<DataModel.Light>>
            ) {
                if (response.body() != null) {
                    Log.i("INFO", "All lights is update")
                    val json_lights = gson.toJson(response.body())
                    editor.putString("lights", json_lights)
                    editor.apply()
                }
            }

            override fun onFailure(call: Call<ArrayList<DataModel.Light>>?, t: Throwable?) {
                Toast.makeText(view, "Can`t get lights", Toast.LENGTH_SHORT).show()
                Log.e("ERROR RESPONSE", t.toString())
            }
        })

    }

    fun getCameras(token: String, editor: SharedPreferences.Editor, view: Context) {

        val getCameras = ApiInterface.create().getAllCameras(token)

        getCameras.enqueue(object :
            Callback<ArrayList<DataModel.Camera>> {
            override fun onResponse(
                call: Call<ArrayList<DataModel.Camera>>,
                response: Response<ArrayList<DataModel.Camera>>
            ) {
                if (response.body() != null) {
                    Log.i("INFO", "All cameras is update")
                    val json_cameras = gson.toJson(response.body())
                    editor.putString("cameras", json_cameras)
                    editor.apply()
                }
            }

            override fun onFailure(call: Call<ArrayList<DataModel.Camera>>?, t: Throwable?) {
                Toast.makeText(view, "Can`t get cameras", Toast.LENGTH_SHORT).show()
                Log.e("ERROR RESPONSE", t.toString())
            }
        })
    }

    fun getDetectors(token: String, editor: SharedPreferences.Editor, view: Context) {
        val getDetectors = ApiInterface.create().getAllDetectors(token)
        getDetectors.enqueue(object :
            Callback<ArrayList<DataModel.Detector>> {
            override fun onResponse(
                call: Call<ArrayList<DataModel.Detector>>,
                response: Response<ArrayList<DataModel.Detector>>
            ) {
                if (response.body() != null) {
                    Log.i("INFO", "All detectors is update")
                    val json_detectors = gson.toJson(response.body())
                    editor.putString("detectors", json_detectors)
                    editor.apply()
                }
            }

            override fun onFailure(call: Call<ArrayList<DataModel.Detector>>?, t: Throwable?) {
                Toast.makeText(view, "Response Failed detector", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun createLight(view: Context?, deviceName: String?, devicePlace: String?) {
        val token = view?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
            ?.getString("token", null)
        val createLight = ApiInterface.create().createLight(
            token,
            DataModel.Light(
                deviceName,
                devicePlace,
                false
            )
        )

        createLight.enqueue(object :
            Callback<DataModel.Light> {
            override fun onResponse(
                call: Call<DataModel.Light>,
                response: Response<DataModel.Light>
            ) {
                if (response.body() != null) {
                    Toast.makeText(view, "Лампочка создана", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DataModel.Light>?, t: Throwable?) {
                Toast.makeText(view, "Response Failed detector", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun deleteLight(view: Context?, id: String) {
        val token = view?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
            ?.getString("token", null)
        val deleteLight = ApiInterface.create().deleteLight(token, id)

        deleteLight.enqueue(object :
            Callback<DataModel.Light> {
            override fun onResponse(
                call: Call<DataModel.Light>,
                response: Response<DataModel.Light>
            ) {
                if (response.body() != null) {
                    Toast.makeText(view, "Лампочка удалена", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DataModel.Light>?, t: Throwable?) {
                Toast.makeText(view, "Response Failed detector", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun createCamera(view: Context?, deviceName: String?, devicePlace: String?) {
        val token = view?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
            ?.getString("token", null)
        val createCamera = ApiInterface.create().createCamera(
            token,
            DataModel.Camera(
                deviceName,
                devicePlace
            )
        )

        createCamera.enqueue(object :
            Callback<DataModel.Camera> {
            override fun onResponse(
                call: Call<DataModel.Camera>,
                response: Response<DataModel.Camera>
            ) {
                if (response.body() != null) {
                    Toast.makeText(view, "Камера создана", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DataModel.Camera>?, t: Throwable?) {
                Toast.makeText(view, "Response Failed detector", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun deleteCamera(view: Context?, id: String) {
        val token = view?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
            ?.getString("token", null)
        val deleteCamera = ApiInterface.create().deleteCamera(token, id)

        deleteCamera.enqueue(object :
            Callback<DataModel.Camera> {
            override fun onResponse(
                call: Call<DataModel.Camera>,
                response: Response<DataModel.Camera>
            ) {
                if (response.body() != null) {
                    Toast.makeText(view, "Камера удалена", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DataModel.Camera>?, t: Throwable?) {
                Toast.makeText(view, "Response Failed camera", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun createDetector(view: Context?, deviceName: String?, devicePlace: String?) {
        val token = view?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
            ?.getString("token", null)
        val createDetector = ApiInterface.create().createDetector(
            token,
            DataModel.Detector(
                deviceName,
                devicePlace,
                "null"
            )
        )

        createDetector.enqueue(object :
            Callback<DataModel.Detector> {
            override fun onResponse(
                call: Call<DataModel.Detector>,
                response: Response<DataModel.Detector>
            ) {
                if (response.body() != null) {
                    Toast.makeText(view, "Детектор создан", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DataModel.Detector>?, t: Throwable?) {
                Toast.makeText(view, "Response Failed detector", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun deleteDetector(view: Context?, id: String) {
        val token = view?.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
            ?.getString("token", null)
        val deleteDetector = ApiInterface.create().deleteDetector(token, id)

        deleteDetector.enqueue(object :
            Callback<DataModel.Detector> {
            override fun onResponse(
                call: Call<DataModel.Detector>,
                response: Response<DataModel.Detector>
            ) {
                if (response.body() != null) {
                    Toast.makeText(view, "Детектор удален", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DataModel.Detector>?, t: Throwable?) {
                Toast.makeText(view, "Response Failed detector", Toast.LENGTH_SHORT).show()
            }
        })
    }

}