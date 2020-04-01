package juhchamp.com.br.featureintro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import juhchamp.com.br.feature_intro.FeatureIntro
import juhchamp.com.br.feature_intro.fragments.FeatureIntroFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mode_one_button.setOnClickListener {
            startActivity(Intent(this, ModeOneActivity::class.java))
        }

        mode_two_button.setOnClickListener {
            startActivity(Intent(this, ModeTwoActivity::class.java))
        }
    }
}
