package com.esiea.project.presentation.list

import android.content.Context
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
import com.esiea.project.data.local.models.Doctor
import com.esiea.project.injection.Singletons
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MyListActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: MyListAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var controller: ListController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        controller = ListController(
            this,
            Singletons.getGson(),
            Singletons.getSharedPreferences(applicationContext)
        )
        controller!!.onStart()

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

    fun showList(doctorList: List<Doctor>) {
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        mAdapter = MyListAdapter(doctorList as ArrayList<Doctor>)
        recyclerView!!.adapter = mAdapter
    }

    fun showError() {
        Toast.makeText(applicationContext, Constants.error, Toast.LENGTH_SHORT).show()
    }
}