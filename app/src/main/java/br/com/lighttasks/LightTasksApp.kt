package br.com.lighttasks

import android.app.Application
import br.com.lighttasks.commom.util.PreferencesWrapper
import br.com.lighttasks.presentation.data.remote.service.di.apiModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class LightTasksApp : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesWrapper.initPreferences(this)
        initKoinModules()
    }

    private fun initKoinModules() {
        startKoin {
            androidContext(this@LightTasksApp)
            androidFileProperties()
            modules(
                apiModules
            )
        }
    }
}