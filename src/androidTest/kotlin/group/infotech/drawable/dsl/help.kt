package group.infotech.drawable.dsl

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

fun snapshot(d: Drawable): Bitmap {

  val width = d.intrinsicWidth.takeIf { it > 0 } ?: 256
  val height = d.intrinsicHeight.takeIf { it > 0 } ?: 256

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
