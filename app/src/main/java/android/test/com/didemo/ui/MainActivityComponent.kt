package android.test.com.didemo.ui

import android.test.com.didemo.di.component.UserComponent
import dagger.Component

@Component(modules = [MainActivityModule::class], dependencies = [UserComponent::class])
@MainActivityScope
interface MainActivityComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}