package com.esiea.project.injection

import android.content.Context
import android.content.SharedPreferences
import com.esiea.project.data.local.Constants
import com.esiea.project.domain.api.DoctorApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Singletons {

    companion object {

        private var gsonInstance: Gson? = null
        private var doctorApiInstance: DoctorApi? = null
        private var sharedPreferencesInstance: SharedPreferences? = null

        fun getGson(): Gson {
            if (gsonInstance == null) {
                gsonInstance = GsonBuilder().setLenient().create()
            }
            return gsonInstance!!
        }

        fun getDoctorApi(): DoctorApi {
            if (doctorApiInstance == null) {
                val retrofit = Retrofit
                    .Builder()
                    .baseUrl(Constants.api_url)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build()
                doctorApiInstance = retrofit.create(DoctorApi::class.java)
            }
            return doctorApiInstance!!
        }

        fun getSharedPreferences(context: Context) : SharedPreferences {
            if(sharedPreferencesInstance == null) {
                sharedPreferencesInstance = context.getSharedPreferences(Constants.name_shared_prefs, Context.MODE_PRIVATE)
            }
            return sharedPreferencesInstance!!
        }
    }
}