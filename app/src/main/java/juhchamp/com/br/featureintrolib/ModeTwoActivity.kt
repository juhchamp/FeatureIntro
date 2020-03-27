package juhchamp.com.br.featureintrolib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import juhchamp.com.br.feature_intro.FeatureIntro
import juhchamp.com.br.featureintrolib.fragments.CustomFragmentOne
import kotlinx.android.synthetic.main.activity_mode_two.*

class ModeTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode_two)
        initWithCustom()
    }

    private fun initWithCustom() {
        val customFrags: MutableList<Fragment> = mutableListOf()

        customFrags.add(0, CustomFragmentOne.newInstance(
            R.drawable.ic_android_black_64dp,
            "Hello World",
            "Hello human! You're ok?"
        ))

        customFrags.add(1, CustomFragmentOne.newInstance(
            R.drawable.ic_launcher_foreground,
            "ic_launcher_foreground",
            "Hello ic_launcher_foreground!"
        ))

        customFrags.add(2, CustomFragmentOne.newInstance(
            R.drawable.ic_launcher_background,
            "ic_launcher_background",
            "Hello ic_launcher_background!"
        ))


        feature_intro_view
            .setFinishButtonText("Next")
            .withCustom(this, customFrags)
            .setup(object: FeatureIntro.FeatureIntroInteractionListener {
                override fun onFinishButtonClick() {
                    Toast.makeText(this@ModeTwoActivity, "Next", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
