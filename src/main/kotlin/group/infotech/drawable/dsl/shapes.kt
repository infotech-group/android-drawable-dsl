package group.infotech.drawable.dsl

import android.graphics.drawable.GradientDrawable

inline fun shapeDrawable(block: GradientDrawable.() -> Unit): GradientDrawable =
    GradientDrawable().also {
      it.gradientType = GradientDrawable.LINEAR_GRADIENT
      it.block()
    }

fun rectangleShape(radius: Float? = null, color: ColorInt, size: Int? = null): GradientDrawable =
    shapeDrawable {
      shape = GradientDrawable.RECTANGLE

      // DO NOT CHANGE
      // RADIUS AND COLOR ORDER IS IMPORTANT FOR RIPPLES!
      radius?.let {
        cornerRadius = it
      }

      solidColor = color

      size?.let {
        this.size = it
      }
    }

fun circleShape(color: ColorInt, size: Px? = null): GradientDrawable =
    shapeDrawable {
      shape = GradientDrawable.OVAL
      solidColor = color

      size?.let {
        this.size = it
      }
    }

var GradientDrawable.solidColor: ColorInt
  set(value) = setColor(value)
  @Deprecated(message = NO_GETTER, level = DeprecationLevel.ERROR) get() = error(NO_GETTER)

var GradientDrawable.size: Px
  set(value) = setSize(value, value)
  get() = intrinsicWidth

class Stroke {
  var width: Px = -1
  var color: ColorInt = -1
  var dashWidth: FloatPx = 0F
  var dashGap: FloatPx = 0F
}

inline fun <T> GradientDrawable.stroke(fill: Stroke.() -> T): T =
    Stroke().let { s ->
      s.fill().also {
        setStroke(s.width, s.color, s.dashWidth, s.dashGap)
      }
    }

class Size {
  var width: Px = -1
  var height: Px = -1
}

inline fun <T> GradientDrawable.size(fill: Size.() -> T): T =
    Size().let { s ->
      s.fill().also {
        setSize(s.width, s.height)
      }
    }
