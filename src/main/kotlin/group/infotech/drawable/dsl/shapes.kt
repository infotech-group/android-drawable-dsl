package group.infotech.drawable.dsl

import android.annotation.TargetApi
import android.graphics.drawable.GradientDrawable

inline fun shapeDrawable(block: GradientDrawable.() -> Unit): GradientDrawable =
    GradientDrawable().apply {
      gradientType = GradientDrawable.LINEAR_GRADIENT
      block()
    }

enum class Shape {
  RECTANGLE,
  OVAL,
  LINE,
  RING,
}

typealias ShapeInt = Int

fun toInt(s: Shape): ShapeInt =
    when (s) {
      Shape.RECTANGLE -> GradientDrawable.RECTANGLE
      Shape.OVAL      -> GradientDrawable.OVAL
      Shape.LINE      -> GradientDrawable.LINE
      Shape.RING      -> GradientDrawable.RING
    }

fun fromInt(s: ShapeInt): Shape? =
    when (s) {
      GradientDrawable.RECTANGLE -> Shape.RECTANGLE
      GradientDrawable.OVAL      -> Shape.OVAL
      GradientDrawable.LINE      -> Shape.LINE
      GradientDrawable.RING      -> Shape.RING
      else                       -> null
    }

var GradientDrawable.shapeEnum: Shape
  set(value) {
    shape = toInt(value)
  }
  @TargetApi(24) get() = fromInt(shape) ?: error("Illegal shape int $shape")

fun rectangleShape(radius: FloatPx = Float.NaN,
                   color: ColorInt,
                   size: Px? = null): GradientDrawable =
    shapeDrawable {
      shapeEnum = Shape.RECTANGLE

      // DO NOT CHANGE
      // RADIUS AND COLOR ORDER IS IMPORTANT FOR RIPPLES!
      if (radius != Float.NaN) {
        cornerRadius = radius
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

inline fun GradientDrawable.stroke(fill: Stroke.() -> Unit) =
    Stroke().apply {
      fill()
      setStroke(width, color, dashWidth, dashGap)
    }

class Size {
  var width: Px = -1
  var height: Px = -1
}

inline fun GradientDrawable.size(fill: Size.() -> Unit) =
    Size().apply {
      fill()
      setSize(width, height)
    }