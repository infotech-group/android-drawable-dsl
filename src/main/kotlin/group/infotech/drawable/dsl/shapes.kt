package group.infotech.drawable.dsl

import android.annotation.TargetApi
import android.graphics.drawable.GradientDrawable

inline fun shapeDrawable(fill: GradientDrawable.() -> Unit): GradientDrawable =
    GradientDrawable().also {
        it.gradientType = GradientDrawable.LINEAR_GRADIENT
        it.fill()
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
        Shape.RECTANGLE ->
            GradientDrawable.RECTANGLE

        Shape.OVAL ->
            GradientDrawable.OVAL

        Shape.LINE ->
            GradientDrawable.LINE

        Shape.RING ->
            GradientDrawable.RING
    }

fun fromInt(s: ShapeInt): Shape? =
    when (s) {
        GradientDrawable.RECTANGLE ->
            Shape.RECTANGLE

        GradientDrawable.OVAL ->
            Shape.OVAL

        GradientDrawable.LINE ->
            Shape.LINE

        GradientDrawable.RING ->
            Shape.RING

        else ->
            null
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
    @Deprecated(message = NO_GETTER, level = DeprecationLevel.HIDDEN) get() = error(NO_GETTER)

var GradientDrawable.size: Px
    set(value) = setSize(value, value)
    get() = intrinsicWidth

class Stroke {
    var width: Px = -1
    var color: ColorInt = -1
    var dashWidth: FloatPx = 0F
    var dashGap: FloatPx = 0F
}

inline fun GradientDrawable.stroke(fill: Stroke.() -> Unit): Stroke =
    Stroke().also {
        it.fill()
        setStroke(it.width, it.color, it.dashWidth, it.dashGap)
    }

class Size {
    var width: Px = -1
    var height: Px = -1
}

inline fun GradientDrawable.size(fill: Size.() -> Unit): Size =
    Size().also {
        fill(it)
        setSize(it.width, it.height)
    }

class Corners {
    var radius: FloatPx = 0F

    var topLeft: FloatPx = Float.NaN
    var topRight: FloatPx = Float.NaN
    var bottomLeft: FloatPx = Float.NaN
    var bottomRight: FloatPx = Float.NaN

    internal fun FloatPx.orRadius(): FloatPx =
        takeIf { it >= 0 } ?: radius
}

fun Corners.render(): FloatArray =
    floatArrayOf(
        topLeft.orRadius(),
        topLeft.orRadius(),

        topRight.orRadius(),
        topRight.orRadius(),

        bottomRight.orRadius(),
        bottomRight.orRadius(),

        bottomLeft.orRadius(),
        bottomLeft.orRadius()
    )

inline fun GradientDrawable.corners(fill: Corners.() -> Unit): Corners =
    Corners().also {
        it.fill()
        cornerRadii = it.render()
    }
