package com.esiea.project.presentation.main

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import com.esiea.project.R
import com.esiea.project.presentation.list.MyListActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loginLiveData.observe(this, Observer {
            when (it) {
                is LoginSuccess -> {
                    val intent = Intent(this, MyListActivity::class.java)
                    startActivity(intent)
                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Compte inconnu ou mot de passe incorrect")
                        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
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
                        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                        .show()
                }
                RegisterError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Email ou mot de passe vide, ou compte déjà existant.")
                        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
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