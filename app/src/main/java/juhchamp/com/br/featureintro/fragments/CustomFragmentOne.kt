package juhchamp.com.br.featureintro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import juhchamp.com.br.featureintro.R
import kotlinx.android.synthetic.main.fragment_custom_one.*

private const val IMAGE_ID_ARG = "imageid"
private const val TITLE_ARG = "title"
private const val TEXT_ARG = "text"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomFragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomFragmentOne : Fragment() {

    private var imageId: Int? = null
    private var title: String? = null
    private var text: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageId = it.getInt(IMAGE_ID_ARG)
            title = it.getString(TITLE_ARG)
            text = it.getString(TEXT_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_one, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        image_iv.setImageResource(imageId!!)
        title_tv.text = title
        resume_tv.text = text
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param imageId Image Resource Id of the feature.
         * @param title Title of the feature.
         * @param text Text explaining the feature.
         * @return A new instance of fragment FeatureIntroFragment.
         */
        @JvmStatic
        fun newInstance(imageId: Int, title: String, text: String) =
            CustomFragmentOne().apply {
                arguments = Bundle().apply {
                    putInt(IMAGE_ID_ARG, imageId)
                    putString(TITLE_ARG, title)
                    putString(TEXT_ARG, text)
                }
            }
    }
}
