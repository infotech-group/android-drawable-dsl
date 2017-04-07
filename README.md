android-drawable-dsl
====================

DSL for constructing the drawables in Kotlin instead of in XML

# Usage

## Shape drawables

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <solid android:color="#199fff" />
    <stroke
        android:width="2dp"
        android:color="#444444" />
</shape>
```

replace it with

```kotlin
shapeDrawable {
  shape = GradientDrawable.OVAL

  solidColor = Color.parseColor("#199fff")

  stroke {
    width = dip(2)
    color = Color.parseColor("#444444")
  }
}
```

## State selectors

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:state_checked="true">
        <shape>
            <solid android:color="@android:color/black" />
        </shape>
    </item>

    <item android:state_pressed="true">
        <shape>
            <solid android:color="@android:color/holo_red_dark" />
        </shape>
    </item>

    <item>
        <shape>
            <solid android:color="@android:color/white" />
        </shape>
    </item>
</selector>
```

replace it with

```kotlin
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
```
