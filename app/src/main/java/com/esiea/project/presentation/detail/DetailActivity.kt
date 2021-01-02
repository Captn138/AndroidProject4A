package com.esiea.project.presentation.detail

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.esiea.project.R
import com.esiea.project.data.local.Constants
import com.esiea.project.data.local.models.Doctor
import com.esiea.project.injection.Singletons
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val jsonDoctor = intent.getStringExtra(Constants.key_doctor_list)
        val doctor = Singletons.getGson().fromJson(jsonDoctor, Doctor::class.java)

        showDetails(doctor)


        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES ->
                dark_theme_switch2.isChecked = true
            Configuration.UI_MODE_NIGHT_NO ->
                dark_theme_switch2.isChecked = false
        }

        dark_theme_switch2.setOnClickListener {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    dark_theme_switch2.isChecked = false
                }
                Configuration.UI_MODE_NIGHT_NO -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    dark_theme_switch2.isChecked = true
                }
            }
        }
    }

    private fun showDetails(doctor: Doctor) {
        text_view_id.text = "Docteur n°${doctor.id}"
        text_view_incarnation.text = "Incarnation: ${doctor.incarnation}"
        val imageResource = resources.getIdentifier("@drawable/d${doctor.id}", null, packageName)
        val res = resources.getDrawable(imageResource, null)
        image_view.setImageDrawable(res)
    }
}
