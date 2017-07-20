package work.beltran.emojitalk

import android.app.Application
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.bundled.BundledEmojiCompatConfig


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val config = BundledEmojiCompatConfig(this)
//        config.setReplaceAll(true)
        EmojiCompat.init(config)
    }
}