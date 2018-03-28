package work.beltran.emojitalk

import android.content.Context
import android.text.SpannableString
import android.text.style.ImageSpan


fun parseEmoji(text: String, context: Context): SpannableString {
    val spannableString = SpannableString(text)

    val PATTERN = "\\ud83c\\udf55".toRegex()
    val matches = PATTERN.findAll(text)

    for (match in matches) {
        spannableString.setSpan(
                ImageSpan(context, R.drawable.emoji_1f355),
                match.range.start,
                match.range.endInclusive + 1,
                SpannableString.SPAN_INCLUSIVE_INCLUSIVE
        )
    }

    return spannableString
}