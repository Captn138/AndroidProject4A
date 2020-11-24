package com.esiea.project.injection

import com.esiea.project.data.repository.UserRepository
import com.esiea.project.domain.usecase.CreateUserUseCase
import com.esiea.project.presentation.main.MainViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val presentationModule = module {
    factory { MainViewModel(get()) }
}

val domainModule : Module = module {
    factory { CreateUserUseCase(get()) }
}

val dataModule : Module = module {
    single { UserRepository() }
}