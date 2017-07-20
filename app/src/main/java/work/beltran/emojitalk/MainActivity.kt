package work.beltran.emojitalk

import android.os.Bundle
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
        textView = findViewById(R.id.text_view) as TextView
        textView2 = findViewById(R.id.text_view_2) as TextView
        textView3 = findViewById(R.id.text_view_3) as TextView
        textView4 = findViewById(R.id.text_view_4) as TextView

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
