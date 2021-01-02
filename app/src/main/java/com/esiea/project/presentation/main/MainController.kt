package com.esiea.project.presentation.main

import android.content.Intent
import androidx.lifecycle.Observer
import com.esiea.project.data.local.models.LoginError
import com.esiea.project.data.local.models.LoginSuccess
import com.esiea.project.data.local.models.RegisterError
import com.esiea.project.data.local.models.RegisterSuccess
import com.esiea.project.presentation.list.MyListActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainController(view: MainActivity, mainViewModel: MainViewModel) {

    private val view = view
    private val mainViewModel = mainViewModel

    fun onStart() {
        mainViewModel.loginLiveData.observe(view, Observer {
            when (it) {
                is LoginSuccess -> {
                    val intent = Intent(view, MyListActivity::class.java)
                    view.startActivity(intent)
                }
                LoginError -> {
                    MaterialAlertDialogBuilder(view)
                        .setTitle("Erreur")
                        .setMessage("Compte inconnu ou mot de passe incorrect")
                        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                        .show()
                }
            }
        })

        mainViewModel.registerLiveData.observe(view, Observer {
            when (it) {
                is RegisterSuccess -> {
                    MaterialAlertDialogBuilder(view)
                        .setTitle("Succès")
                        .setMessage("Votre compte a été créé. Vous pouvez désormais vous connecter.")
                        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                        .show()
                }
                RegisterError -> {
                    MaterialAlertDialogBuilder(view)
                        .setTitle("Erreur")
                        .setMessage("Email ou mot de passe vide, ou compte déjà existant.")
                        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                        .show()
                }
            }
        })

        view.login_button.setOnClickListener {
            mainViewModel.onClickedLogin(
                view.login_edit.text.toString().trim(),
                view.password_edit.text.toString()
            )
        }

        view.create_account_button.setOnClickListener {
            mainViewModel.onClickedRegister(
                view.login_edit.text.toString().trim(),
                view.password_edit.text.toString()
            )
        }
    }
}