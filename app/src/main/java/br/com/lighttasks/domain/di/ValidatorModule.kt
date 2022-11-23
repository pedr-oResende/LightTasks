package br.com.lighttasks.domain.di

import br.com.lighttasks.domain.validator.ValidatePassword
import br.com.lighttasks.domain.validator.ValidateUsername
import org.koin.dsl.module

val validatorModules = module {

    single { ValidateUsername() }

    single { ValidatePassword() }

}