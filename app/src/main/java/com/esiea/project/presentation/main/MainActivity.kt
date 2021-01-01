package com.esiea.project.presentation.main

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.esiea.project.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loginLiveData.observe(this, Observer {
            when (it) {
                is LoginSuccess -> TODO()
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Compte inconnu ou mot de passe incorrect")
                        .setPositiveButton("OK") { dialog, which -> dialog.dismiss() }
                        .show()
                }
            }
        })

        mainViewModel.registerLiveData.observe(this, Observer {
            when (it) {
                is RegisterSuccess -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Succès")
                        .setMessage("Votre compte a été créé. Vous pouvez désormais vous connecter.")
                        .setPositiveButton("OK") { dialog, which -> dialog.dismiss() }
                        .show()
                }
                RegisterError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Email ou mot de passe vide")
                        .setPositiveButton("OK") { dialog, which -> dialog.dismiss() }
                        .show()
                }
            }
        })

        login_button.setOnClickListener {
            mainViewModel.onClickedLogin(
                login_edit.text.toString().trim(),
                password_edit.text.toString()
            )
        }

        create_account_button.setOnClickListener {
            mainViewModel.onClickedRegister(
                login_edit.text.toString().trim(),
                password_edit.text.toString()
            )
        }

        val isNightTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (isNightTheme) {
            Configuration.UI_MODE_NIGHT_YES ->
                dark_theme_switch.setChecked(true)
            Configuration.UI_MODE_NIGHT_NO ->
                dark_theme_switch.setChecked(false)
        }

        dark_theme_switch.setOnClickListener {
            val isNightTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            when (isNightTheme) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    dark_theme_switch.setChecked(false)
                }
                Configuration.UI_MODE_NIGHT_NO -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    dark_theme_switch.setChecked(true)
                }
            }
        }
    }
}