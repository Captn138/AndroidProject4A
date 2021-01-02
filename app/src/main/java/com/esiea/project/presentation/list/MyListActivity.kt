package com.esiea.project.presentation.list

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esiea.project.R
import com.esiea.project.data.local.models.Constants
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_main.dark_theme_switch
import kotlin.reflect.jvm.internal.impl.load.java.Constant

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
        val input1: ArrayList<String> = ArrayList()
        val input2: ArrayList<String> = ArrayList()

        for (i in 0..99) {
            input1.add("Test$i")
            input2.add(Constants.test)
        }

        mAdapter = MyListAdapter(input2)
        recyclerView!!.adapter = mAdapter

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
}