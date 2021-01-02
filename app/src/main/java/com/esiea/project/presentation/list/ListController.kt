package com.esiea.project.presentation.list

import android.content.SharedPreferences
import android.widget.Toast
import com.esiea.project.data.local.Constants
import com.esiea.project.data.local.models.Doctor
import com.esiea.project.injection.Singletons
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListController(private val view: MyListActivity, private val gson: Gson, private val sharedPreferences: SharedPreferences) {

    fun onStart() {
        val doctorList: List<Doctor>? = getDataFromCache()
        if (doctorList != null) {
            view.showList(doctorList)
        } else {
            makeApiCall()
        }
    }

    private fun getDataFromCache(): List<Doctor>? {
        val jsonDoctor = sharedPreferences.getString(Constants.key_doctor_list, null)
        return if (jsonDoctor == null) {
            null
        } else {
            gson.fromJson(jsonDoctor, object : TypeToken<List<Doctor?>?>() {}.type)
        }

    }

    private fun makeApiCall() {
        val call: Call<List<Doctor>> = Singletons.getDoctorApi().doctorResponse
        call.enqueue(object : Callback<List<Doctor>> {
            override fun onFailure(call: Call<List<Doctor>>?, t: Throwable) {
                view.showError()
            }

            override fun onResponse(call: Call<List<Doctor>>?, response: Response<List<Doctor>>?) {
                if (response!!.isSuccessful && response.body() != null) {
                    val doctorList: List<Doctor> = response.body()!!.filterNotNull()
                    saveList(doctorList)
                    view.showList(doctorList)
                } else {
                    view.showError()
                }
            }
        })
    }

    private fun saveList(doctorList: List<Doctor>) {
        val jsonDoctorString: String = gson.toJson(doctorList)
        sharedPreferences
            .edit()
            .putString(Constants.key_doctor_list, jsonDoctorString)
            .apply()
    }

    fun onItemClick(doctor: Doctor) {
        view.navigateToDetails(doctor)
    }
}