package juhchamp.com.br.featureintrolib

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
                "Revenda serviços",
                "Use seu telemóvel para ganhar dinheiro e receba a comissão da venda na hora. Vendeu, recebeu!"
            )
            .addStep(
                R.drawable.ilustracao_tutorial_revendedores_2,
                "Até 8% de comissão",
                "Não precisa de aprovação para revender. É só depositar, revender e receber sua comissão."
            )
            .addStep(
                R.drawable.ilustracao_tela_revendedores_3,
                "Deposite dinheiro na sua carteira para começar a revender",
                "Oferecemos a comissão na hora da venda. Seu negócio cresce quanto mais você vende."
            )
            .addStep(
                R.drawable.ilustracao_tela_revendedores_4,
                "Assista seu dinheiro render",
                "Acompanhe seu lucro mensal com a ferramenta mais avançada do mercado. Sem taxas ou números escondidos."
            )
            .addStep(
                R.drawable.ilustracao_tutorial_revendedores_5,
                "Levante seu dinheiro há qualquer momento",
                "Você não paga nenhuma taxa ou assina contratos. Não temos burocracia. Queremos o seu crescimento."
            )
            .setup(this)
    }

    private fun initWithDefaultFragmentList() {
        val frags: MutableList<FeatureIntroFragment> = mutableListOf()
        frags.add(0,
            FeatureIntroFragment.newInstance(
                R.drawable.ic_launcher_background,
                "Hello world",
                "This is step 1 he."
            )
        )

        frags.add(1,
            FeatureIntroFragment.newInstance(
                R.drawable.ic_launcher_foreground,
                "Hello world 2",
                "This is step 2 hehe."
            )
        )

        feature_intro_view.
            withDefault(this, frags)
            .setFinishButtonText("Continue")
            .setup(object: FeatureIntro.FeatureIntroInteractionListener {
                override fun onFinishButtonClick() {
                    Toast.makeText(this@MainActivity, "Continue.", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onFinishButtonClick() {
        Toast.makeText(this@MainActivity, "onFinishButtonClick", Toast.LENGTH_SHORT).show()
    }
}
