package group.infotech.drawable.dsl

import android.content.res.ColorStateList
import android.graphics.PorterDuff
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

fun Drawable.copy(): Drawable =
    constantState.newDrawable().mutate()

fun rippleCompat(color: ColorInt, content: Drawable, mask: Drawable? = null): Drawable =
    when {
        Build.VERSION.SDK_INT >= 21 ->
            RippleDrawable(ColorStateList.valueOf(color), content, mask)

        else ->
            stateListDrawable {
                exitFadeDuration = 300

                pressedState {
                    content.copy().apply {
                        setColorFilter(color, PorterDuff.Mode.SRC_IN)
                    }
                }

                defaultState {
                    content
                }
            }
    }

/**
 * the point of this is varargs
 */
fun layerDrawable(vararg ds: Drawable): LayerDrawable =
    LayerDrawable(ds)
