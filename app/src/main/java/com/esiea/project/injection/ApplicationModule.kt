package com.esiea.project.injection

import android.content.Context
import androidx.room.Room
import com.esiea.project.data.local.DatabaseDao
import com.esiea.project.data.local.models.AppDatabase
import com.esiea.project.data.repository.UserRepository
import com.esiea.project.domain.usecase.CreateUserUseCase
import com.esiea.project.domain.usecase.GetUserUseCase
import com.esiea.project.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val presentationModule : Module = module {
    factory { MainViewModel(get(), get()) }
}

val domainModule : Module = module {
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
}

val dataModule : Module = module {
    single { UserRepository(get()) }
    single { createDataBase(androidContext()) }
}

fun createDataBase(context: Context): DatabaseDao {
    val appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()
    return appDatabase.databaseDao()
}
