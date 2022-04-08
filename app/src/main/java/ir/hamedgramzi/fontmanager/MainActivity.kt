package ir.hamedgramzi.fontmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.hamedgramzi.lib.FontManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FontManager.instance().setTypeface(this)
    }
}