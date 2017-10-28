package group.infotech.drawable.dsl

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import group.infotech.drawable.dsl.test.R
import io.kotlintest.matchers.should
import org.jetbrains.anko.dip
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SnapshotTest {

    val ctx: Context = InstrumentationRegistry.getTargetContext()

    @Test
    fun redCircle() {

        val dsl =
            shapeDrawable {
                shape = GradientDrawable.OVAL
                solidColor = Color.parseColor("#666666")
                size = ctx.dip(120)
            }

        dsl should drawPixelsLike(ctx.drawable(R.drawable.redcircle))
    }

    @Test
    fun blueStroke() {

        val dsl =
            shapeDrawable {
                shape = GradientDrawable.OVAL

                stroke {
                    width = ctx.dip(2F)
                    color = Color.parseColor("#78d9ff")
                }
            }

        dsl should drawPixelsLike(ctx.drawable(R.drawable.bluestroke))
    }

    @Test
    fun solidStroke() {

        val dsl =
            shapeDrawable {
                shape = GradientDrawable.OVAL

                solidColor = Color.parseColor("#199fff")

                stroke {
                    width = ctx.dip(2)
                    color = Color.parseColor("#444444")
                }
            }

        dsl should drawPixelsLike(ctx.drawable(R.drawable.solidstroke))
    }

    @Test
    fun states() {

        val check = { d: Drawable -> d.state = ViewStates.checked() }
        val uncheck = { d: Drawable -> d.state = ViewStates.unchecked() }

        val dsl =
            stateListDrawable {

                checkedState {
                    shapeDrawable {
                        solidColor = Color.BLACK
                    }
                }

                pressedState {
                    shapeDrawable {
                        solidColor = Color.RED
                    }
                }

                defaultState {
                    shapeDrawable {
                        solidColor = Color.WHITE
                    }
                }
            }

        val xml = ctx.drawable(R.drawable.states)

        dsl.also(check) should drawPixelsLike(xml.also(check))
        dsl.also(uncheck) should drawPixelsLike(xml.also(uncheck))
    }

    @Test
    fun sizes() {

        val dsl =
            shapeDrawable {
                shape = GradientDrawable.OVAL
                solidColor = Color.parseColor("#666666")

                size {
                    width = ctx.dip(120)
                    height = ctx.dip(240)
                }
            }

        val xml = ctx.drawable(R.drawable.sizes)

        dsl should drawPixelsLike(xml)
    }

    @Test
    fun layers() {

        val dsl =
            layerDrawable(

                shapeDrawable {
                    shape = GradientDrawable.OVAL

                    stroke {
                        width = 50
                        color = Color.parseColor("#000000")
                    }
                },

                shapeDrawable {
                    shape = GradientDrawable.OVAL

                    stroke {
                        width = 20
                        color = Color.parseColor("#ffffff")
                    }
                }
            )

        dsl should drawPixelsLike(ctx.drawable(R.drawable.layers))
    }

    @Test
    fun corners() {

        val dsl = shapeDrawable {

            shapeEnum = Shape.RECTANGLE

            corners {
                bottomRight = 5f
                radius = 10f
                topLeft = 15f
            }

            solidColor = Color.parseColor("#666666")
        }

        dsl should drawPixelsLike(ctx.drawable(R.drawable.corners))
    }
}
