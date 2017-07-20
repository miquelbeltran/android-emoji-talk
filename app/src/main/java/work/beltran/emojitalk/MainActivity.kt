package work.beltran.emojitalk

import android.os.Bundle
import android.support.text.emoji.EmojiCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import java.text.BreakIterator



class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var textView2: TextView
    lateinit var textView3: TextView
    lateinit var textView4: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.text_view)
        textView2 = findViewById<TextView>(R.id.text_view_2)
        textView3 = findViewById<TextView>(R.id.text_view_3)
        textView4 = findViewById<TextView>(R.id.text_view_4)

        // Display Joy Emoji
        val emoji = "\uD83D\uDE02"
        textView.text = emoji

        // Display Woman Developer Emoji
        val emoji2 = "\uD83D\uDC69\uD83C\uDFFE\u200D\uD83D\uDCBB"
        textView2.text = emoji2

        var length = 0

        length = emoji2.length // == 7

        length = Character.codePointCount(emoji2, 0, emoji2.length) // == 4

        length = getLengthUsingBreakIterator(emoji2)
        // == 3 on API 23
        // == 1 on API 25

        Log.i("MainActivity", "Length of Emoji is $length")

        // Takes some milliseconds to be ready
        // Docs: it takes approximately 150 milliseconds to initialize EmojiCompat.
        EmojiCompat.get().registerInitCallback (object : EmojiCompat.InitCallback() {
            override fun onInitialized() {
                val string = "Joy: $emoji\nDeveloper: $emoji2"
                val processed = EmojiCompat.get().process(string)
                textView3.text = processed
            }

            override fun onFailed(throwable: Throwable?) {
                throwable?.printStackTrace()
            }
        })
    }

    private fun getLengthUsingBreakIterator(emoji: String): Int {
        val it = BreakIterator.getCharacterInstance()
        it.setText(emoji)
        var count = 0
        while (it.next() != BreakIterator.DONE) {
            count++
        }
        return count
    }
}
