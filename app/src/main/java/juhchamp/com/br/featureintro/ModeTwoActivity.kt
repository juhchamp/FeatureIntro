package juhchamp.com.br.featureintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import juhchamp.com.br.feature_intro.FeatureIntro
import juhchamp.com.br.featureintro.fragments.CustomFragmentOne
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
            getString(R.string.hello_world),
            getString(R.string.hello_human)
        ))

        customFrags.add(1, CustomFragmentOne.newInstance(
            R.drawable.ic_launcher_foreground,
            getString(R.string.custom_title_1),
            getString(R.string.custom_text_1)
        ))

        customFrags.add(2, CustomFragmentOne.newInstance(
            R.drawable.ic_launcher_background,
            getString(R.string.custom_title_2),
            getString(R.string.custom_text_2)
        ))


        feature_intro_view
            .setFinishButtonText(getString(R.string.next))
            .withCustom(this, customFrags)
            .setup(object: FeatureIntro.FeatureIntroInteractionListener {
                override fun onFinishButtonClick() {
                    Toast.makeText(
                        this@ModeTwoActivity,
                        getString(R.string.next),
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}
