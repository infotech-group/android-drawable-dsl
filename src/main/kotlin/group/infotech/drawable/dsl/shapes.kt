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
      this.shape = GradientDrawable.OVAL
      this.solidColor = color

      size?.let {
        this.size = it
      }
    }

var GradientDrawable.solidColor: ColorInt
  set(value) = setColor(value)
  get() = error("sorry")

var GradientDrawable.size: Px
  set(value) = setSize(value, value)
  get() = intrinsicWidth
