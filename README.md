# FeatureIntro
A simple lib to show introduction for a feature in Android.

[![](https://jitpack.io/v/juhchamp/FeatureIntro.svg)](https://jitpack.io/#juhchamp/FeatureIntro)

## Download
Download the latest version via Gradle:

**Step 1.**
Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

**Step 2.**
Add the SimpleBiometricHandler dependency

```
dependencies {
    implementation 'com.github.juhchamp:FeatureIntro:1.0.0'
}
```

## How to use ?
Add the view in your activity layout:

```kotlin
 <juhchamp.com.br.feature_intro.FeatureIntro
    android:id="@+id/feature_intro_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:maintainBottomMargin="true"
    app:buttonsAllCaps="false"
    app:finishButtonText="Começar"
    app:finishButtonStartVisible="false"/>
```

## Init it in your activity code

```kotlin
feature_intro_view.
  withDefault(this)
  .addStep(
      R.drawable.your_drawable,
      "Example title",
      "Example resume text"
  )
  .addStep(
      R.drawable.your_drawable,
      "Example title 2",
      "Example resume text 2"
  )
  .setup(object: FeatureIntro.FeatureIntroInteractionListener {
      override fun onFinishButtonClick() {
          toast("Finish button clicked")
      }
  })

```

## Use setup with override methods

Make your activity extends the  ```FeatureIntroInteractionListener```
```kotlin
class IntroActivity : AppCompatActivity(), FeatureIntro.FeatureIntroInteractionListener {...}
```

Override the ```onFinishButtonClick()``` method
```kotlin
feature_intro_view.
  withDefault(this)
  .addStep(
      R.drawable.your_drawable,
      "Example title",
      "Example resume text"
  )
  .addStep(
      R.drawable.your_drawable,
      "Example title 2",
      "Example resume text 2"
  )
  .setup(this)

override fun onFinishButtonClick() {
  toast("Finish button clicked")
}

```
## Methods
```kotlin
feature_intro_view.buttonsAllCaps = true or false
feature_intro_view.useCloseButton  = true or false
feature_intro_view.finishButtonText = "Hello world!"
feature_intro_view.finishButtonEnabled = true or false
```