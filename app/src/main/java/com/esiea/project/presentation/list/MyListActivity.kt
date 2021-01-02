package com.esiea.project.presentation.list

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esiea.project.R
import com.esiea.project.data.local.Constants
import com.esiea.project.data.remote.Doctor
import com.esiea.project.domain.api.DoctorApi
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyListActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: MyListAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager

        makeApiCall()

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES ->
                dark_theme_switch.isChecked = true
            Configuration.UI_MODE_NIGHT_NO ->
                dark_theme_switch.isChecked = false
        }

        dark_theme_switch.setOnClickListener {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    dark_theme_switch.isChecked = false
                }
                Configuration.UI_MODE_NIGHT_NO -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    dark_theme_switch.isChecked = true
                }
            }
        }
    }

    private fun makeApiCall() {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit
            .Builder()
            .baseUrl(Constants.api_url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val doctorApi = retrofit.create(DoctorApi::class.java)
        val call: Call<List<Doctor>> = doctorApi.doctorResponse

        call.enqueue(object : Callback<List<Doctor>> {
            override fun onFailure(call: Call<List<Doctor>>?, t: Throwable) {
                showError()
            }

            override fun onResponse(call: Call<List<Doctor>>?, response: Response<List<Doctor>>?) {
                if (response!!.isSuccessful && response.body() != null) {
                    val doctorList: List<Doctor> = response.body()!!.filterNotNull()
                    showList(doctorList)
                } else {
                    showError()
                }
            }
        })
    }

    private fun showList(doctorList: List<Doctor>) {
        mAdapter = MyListAdapter(doctorList as ArrayList<Doctor>)
        recyclerView!!.adapter = mAdapter
    }

    private fun showError() {
        Toast.makeText(applicationContext, Constants.error, Toast.LENGTH_SHORT).show()
    }
}