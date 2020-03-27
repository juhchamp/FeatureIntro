# FeatureIntro
A simple lib to show introduction for a feature in Android.

# How to use ?
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

# Init it in your activity code

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

# Use setup with override methods

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
