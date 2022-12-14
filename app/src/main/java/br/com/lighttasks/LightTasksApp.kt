package br.com.lighttasks

import android.app.Application
import br.com.lighttasks.commom.util.PreferencesWrapper
import br.com.lighttasks.data.di.mapperModules
import br.com.lighttasks.data.di.repositoryModules
import br.com.lighttasks.data.remote.service.di.apiModules
import br.com.lighttasks.domain.di.useCaseModules
import br.com.lighttasks.domain.di.validatorModules
import br.com.lighttasks.presentation.di.viewModelModules
import io.paperdb.Paper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class LightTasksApp : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesWrapper.initPreferences(this)
        Paper.init(this)
        initKoinModules()
    }

    private fun initKoinModules() {
        startKoin {
            androidContext(this@LightTasksApp)
            androidFileProperties()
            modules(
                apiModules,
                repositoryModules,
                mapperModules,
                useCaseModules,
                viewModelModules,
                validatorModules
            )
        }
    }
}