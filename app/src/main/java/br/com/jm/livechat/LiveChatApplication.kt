package br.com.jm.livechat

import android.app.Application
import br.com.jm.livechat.flows.onboarding.SignUpViewModel
import br.com.jm.livechat.storage.LocalStorage
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class LiveChatApplication: Application() {
    private val appModule = module {
        single { LocalStorage(get()) }
        viewModel { SignUpViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LiveChatApplication)
            modules(appModule)
        }
    }
}