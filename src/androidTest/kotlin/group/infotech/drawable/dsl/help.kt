package group.infotech.drawable.dsl

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import io.kotlintest.matchers.Matcher
import io.kotlintest.matchers.Result

fun snapshot(d: Drawable): Bitmap {

    val width = d.intrinsicWidth.takeIf { it > 0 } ?: 512
    val height = d.intrinsicHeight.takeIf { it > 0 } ?: 512

    d.setBounds(0, 0, width, height)

    return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        .also {
            d.draw(Canvas(it))
        }
}

typealias DrawableRes = Int

@Suppress("DEPRECATION")
fun Context.drawable(d: DrawableRes): Drawable =
    resources.getDrawable(d)

fun drawPixelsLike(actual: Drawable): Matcher<Drawable> =
    object : Matcher<Drawable> {
        override fun test(value: Drawable): Result {
            val actB = snapshot(actual)
            val expectedB = snapshot(value)

            return Result(passed = expectedB.sameAs(actB),
                          message = "$value has different pixels than $actual")
        }
    }
