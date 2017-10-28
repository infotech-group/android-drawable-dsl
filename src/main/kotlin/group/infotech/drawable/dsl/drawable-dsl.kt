package group.infotech.drawable.dsl

import android.annotation.TargetApi
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ScaleDrawable
import android.os.Build
import android.view.Gravity

typealias ColorInt = Int
typealias Px = Int
typealias FloatPx = Float
typealias Milliseconds = Int
typealias GravityInt = Int

inline fun scaleDrawable(scale: Float, gravity: GravityInt = Gravity.CENTER, block: () -> Drawable): ScaleDrawable =
    ScaleDrawable(block(), gravity, scale, scale).also {
      // hacking dem android drawables
      it.level = 1
    }

@TargetApi(21)
fun rippleDrawable(color: ColorStateList, content: Drawable?, mask: Drawable?): RippleDrawable =
    RippleDrawable(color, content, mask)

fun maybeRipple(content: Drawable, pressed: Drawable, pressedColor: ColorInt): Drawable =
    if (Build.VERSION.SDK_INT >= 21) {
      rippleDrawable(color = ColorStateList.valueOf(pressedColor),
                     content = content,
                     mask = content)
    } else {
      stateListDrawable {
        exitFadeDuration = 300

        pressedState { pressed }

        defaultState { content }
      }
    }

/**
 * the point of this is varargs
 */
fun layerDrawable(vararg ds: Drawable): LayerDrawable =
    LayerDrawable(ds)
