/*
 * Created by José Jailton da Silva Júnior on 01/04/20 16:50
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 01/04/20 16:50
 * https://github.com/juhchamp
 */

package juhchamp.com.br.featureintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import juhchamp.com.br.feature_intro.FeatureIntro
import juhchamp.com.br.feature_intro.fragments.FeatureIntroFragment
import kotlinx.android.synthetic.main.activity_mode_one.*

class ModeOneActivity : AppCompatActivity(), FeatureIntro.FeatureIntroInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode_one)

        initWithAddStep()
        //initWithDefaultFragmentList()
    }

    override fun onDestroy() {
        super.onDestroy()
        feature_intro_view.destroy()
    }

    private fun initWithAddStep() {
        // feature_intro_view.buttonsAllCaps = false
        // feature_intro_view.useCloseButton  = true
        // feature_intro_view.finishButtonText = "Hello world!"
        // feature_intro_view.finishButtonEnabled = false
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
                    finish()
                }

                override fun onCloseButtonClick() {
                    finish()
                }

                override fun onPageChange(index: Int, itemCount: Int) {
                    Log.w(mTag, "Index: $index, item count: $itemCount")
                }
            })
    }

    override fun onFinishButtonClick() {
        finish()
    }

    override fun onCloseButtonClick() {
        finish()
    }

    override fun onPageChange(index: Int, itemCount: Int) {
        Log.w(mTag, "Index: $index, item count: $itemCount")
        /*if (index >= itemCount) {
            feature_intro_view.finishButtonEnabled = false
        }*/
    }

    companion object {
        const val mTag = "ModeOneActivity"
    }
}
