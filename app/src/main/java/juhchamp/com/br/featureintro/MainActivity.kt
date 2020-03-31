package juhchamp.com.br.featureintro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import juhchamp.com.br.feature_intro.FeatureIntro
import juhchamp.com.br.feature_intro.fragments.FeatureIntroFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FeatureIntro.FeatureIntroInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initWithAddStep()
        //initWithDefaultFragmentList()

        // start mode two activity ( custom fragment )
        mode_two_button.setOnClickListener {
            startActivity(Intent(this, ModeTwoActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        feature_intro_view.destroy()
    }

    private fun initWithAddStep() {
        feature_intro_view.
            withDefault(this)
            .addStep(
                R.drawable.ilustracao_tutorial_revendedores_1,
                getString(R.string.step1_title),
                getString(R.string.step1_text)
            )
            .addStep(
                R.drawable.ilustracao_tutorial_revendedores_2,
                getString(R.string.step2_title),
                getString(R.string.step2_text)
            )
            .addStep(
                R.drawable.ilustracao_tela_revendedores_3,
                getString(R.string.step3_title),
                getString(R.string.step3_text)
            )
            .addStep(
                R.drawable.ilustracao_tela_revendedores_4,
                getString(R.string.step4_title),
                getString(R.string.step4_text)
            )
            .addStep(
                R.drawable.ilustracao_tutorial_revendedores_5,
                getString(R.string.step5_title),
                getString(R.string.step5_text)
            )
            .setup(this)
    }

    private fun initWithDefaultFragmentList() {
        val frags: MutableList<FeatureIntroFragment> = mutableListOf()
        frags.add(0,
            FeatureIntroFragment.newInstance(
                R.drawable.ic_launcher_background,
                getString(R.string.hello_world),
                getString(R.string.this_is_step_1_he)
            )
        )

        frags.add(1,
            FeatureIntroFragment.newInstance(
                R.drawable.ic_launcher_foreground,
                getString(R.string.hello_world_2),
                getString(R.string.this_is_step_2_he_he)
            )
        )

        feature_intro_view.
            withDefault(this, frags)
            .setFinishButtonText(getString(R.string.continue_label))
            .setup(object: FeatureIntro.FeatureIntroInteractionListener {
                override fun onFinishButtonClick() {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.continue_label),
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

    override fun onFinishButtonClick() {
        Toast.makeText(
            this@MainActivity,
            getString(R.string.on_finish_button_click),
            Toast.LENGTH_SHORT)
            .show()
    }
}
