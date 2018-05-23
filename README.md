# Android Drawable Kotlin DSL [![CircleCI](https://circleci.com/gh/infotech-group/android-drawable-dsl.svg?style=svg)](https://circleci.com/gh/infotech-group/android-drawable-dsl)

DSL for constructing the drawables in Kotlin instead of in XML

## Examples

### Shape drawables

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

### State selectors

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

### Layer drawables

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

    <item>
        <shape android:shape="oval">
            <stroke
                android:width="5dp"
                android:color="#000000" />
        </shape>
    </item>

    <item>
        <shape android:shape="oval">
            <stroke
                android:width="2dp"
                android:color="#ffffff" />
        </shape>
    </item>

</layer-list>
```

replace it with

```kotlin
layerDrawable(

    shapeDrawable {
      shape = GradientDrawable.OVAL

      stroke {
        width = ctx.dip(5)
        color = Color.parseColor("#000000")
      }
    },

    shapeDrawable {
      shape = GradientDrawable.OVAL

      stroke {
        width = ctx.dip(2)
        color = Color.parseColor("#ffffff")
      }
    }
)
```

### Install

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

[![](https://jitpack.io/v/infotech-group/android-drawable-dsl.svg)](https://jitpack.io/#infotech-group/android-drawable-dsl)
<a href="http://www.methodscount.com/?lib=com.github.infotech-group%3Aandroid-drawable-dsl%3A0.3.0"><img src="https://img.shields.io/badge/Methods and size-110 | 15 KB-e91e63.svg"/></a>

```groovy
compile "com.github.infotech-group:android-drawable-dsl:0.3.0"
```

### Contribute

We haven't covered 100% of the XML DSL, contributions are very welcome

Please write a [test](/src/androidTest) for every new tag you add, we (hopefully) made it easy to do
