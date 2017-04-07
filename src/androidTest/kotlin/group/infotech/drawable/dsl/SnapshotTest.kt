package group.infotech.drawable.dsl

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.winterbe.expekt.should
import group.infotech.drawable.dsl.test.R
import org.jetbrains.anko.dip
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SnapshotTest {

  val ctx: Context = InstrumentationRegistry.getTargetContext()

  @Test
  fun redCircle() {

    val d =
        shapeDrawable {
          shape = GradientDrawable.OVAL
          solidColor = Color.parseColor("#666666")
          size = ctx.dip(120)
        }

    snapshot(ctx.drawable(R.drawable.redcircle)).sameAs(snapshot(d)).should.be.`true`
  }

  @Test
  fun blueStroke() {

    val d =
        shapeDrawable {
          shape = GradientDrawable.OVAL

          stroke {
            width = ctx.dip(2F)
            color = Color.parseColor("#78d9ff")
          }
        }

    snapshot(ctx.drawable(R.drawable.bluestroke)).sameAs(snapshot(d)).should.be.`true`
  }

  @Test
  fun solidStroke() {

    val d =
shapeDrawable {
  shape = GradientDrawable.OVAL

  solidColor = Color.parseColor("#199fff")

  stroke {
    width = ctx.dip(2)
    color = Color.parseColor("#444444")
  }
}

    snapshot(ctx.drawable(R.drawable.solidstroke)).sameAs(snapshot(d)).should.be.`true`
  }
}
