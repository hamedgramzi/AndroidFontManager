package ir.hamedgramzi.fontmanager

import android.app.Application
import ir.hamedgramzi.lib.FontManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FontManager.init(this, R.string.base_font)
    }
}