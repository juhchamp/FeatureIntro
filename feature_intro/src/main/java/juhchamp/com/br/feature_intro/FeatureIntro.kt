package juhchamp.com.br.feature_intro

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import juhchamp.com.br.feature_intro.fragments.FeatureIntroFragment


class FeatureIntro: RelativeLayout {

    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null
    private var finishButton: Button? = null
    private var adapter: ViewPagerAdapter? = null

    private var buttonsAllCaps: Boolean = true
    private var maintainBottomMargin: Boolean = false
    private var finishButtonStartVisible: Boolean = false
    private var finishButtonText: String = "Finish"

    private var listener: FeatureIntroInteractionListener? = null

    private var fromCustomFragment: Int = -1

    private val onPageChangeListener: ViewPager.OnPageChangeListener = object: ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(state: Int) {
            // no-op
        }

        override fun onPageSelected(position: Int) {
            if (!finishButtonStartVisible) {
                finishButton!!.visibility =
                    if (position >= adapter?.count!!-1) View.VISIBLE
                    else (if (maintainBottomMargin) View.INVISIBLE else View.GONE)
            }
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            // no-op
        }
    }

    constructor(context: Context): super(context) {
        init(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet? = null): super(context, attrs) {
        init(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0): super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FeatureIntro)
        val mRoot = View.inflate(context, R.layout.layout_feature_intro, this)
        viewPager = mRoot.findViewById(R.id.view_pager)
        tabLayout = mRoot.findViewById(R.id.tab_layout)
        finishButton = mRoot.findViewById(R.id.finish_button)

        typedArray.let {
            buttonsAllCaps = it.getBoolean(R.styleable.FeatureIntro_buttonsAllCaps, true)
            maintainBottomMargin = it.getBoolean(R.styleable.FeatureIntro_maintainBottomMargin, true)
            finishButtonStartVisible = it.getBoolean(R.styleable.FeatureIntro_finishButtonStartVisible, false)
            val finishButtonTextIndex = it.getString(R.styleable.FeatureIntro_finishButtonText)
            if (finishButtonTextIndex != null) {
                finishButtonText = finishButtonTextIndex
            }
        }

        finishButton!!.visibility = if (finishButtonStartVisible) View.VISIBLE
        else (if (maintainBottomMargin) View.INVISIBLE else View.GONE)
        finishButton!!.text = finishButtonText
        finishButton!!.setOnClickListener { listener?.onFinishButtonClick() }
        finishButton!!.isAllCaps = buttonsAllCaps

        typedArray.recycle()
    }

    private fun initAdapter(activity: FragmentActivity) {
        when (fromCustomFragment) {
            0 -> throw RuntimeException("withDefault() already been called. " +
                    "Call withDefault() or withCustom() only one time per intro instance.")
            1 -> throw RuntimeException("withCustom() already been called. " +
                    "Call withCustom() or withDefault() only one time per intro instance.")
        }
        adapter = ViewPagerAdapter(activity.supportFragmentManager)
        viewPager?.addOnPageChangeListener(onPageChangeListener)
    }

    /**
     * Set the text of the finishButton appears in the end step of list.
     * @param text string to button text
     */
    fun setFinishButtonText(text: String): FeatureIntro {
        finishButtonText = text
        finishButton!!.text = finishButtonText
        return this
    }

    /**
     * Init feature intro with default fragment system.
     * @param activity the fragmentActivity for get supportFragmentManager
     */
    fun withDefault(activity: FragmentActivity): FeatureIntro {
        initAdapter(activity)
        fromCustomFragment = 0
        return this
    }

    /**
     * Init feature intro with default fragment system.
     * @param activity the fragmentActivity for get supportFragmentManager
     * @param featureIntroFragments a list of [FeatureIntroFragment] to use.
     */
    fun withDefault(activity: FragmentActivity, featureIntroFragments: MutableList<FeatureIntroFragment>): FeatureIntro {
        initAdapter(activity)
        fromCustomFragment = 0
        featureIntroFragments.forEach {
            adapter?.addFragment(it)
        }
        return this
    }

    /**
     * Add step [FeatureIntroFragment] to the intro list setting the image, title, text.
     * @param imageResource a image resource to use for the step
     * @param title a title to use in step
     * @param text a text explaining the step
     */
    fun addStep(imageResource: Int, title: String, text: String): FeatureIntro {
        if (fromCustomFragment == 1) {
            throw RuntimeException(
                "addStep() is only used with the default fragment list. " +
                        "Use withDefault() instead of withCustom()"
            )
        }

        if (adapter == null) {
            throw RuntimeException(
                "call addStep() only after withDefault() call."
            )
        }

        adapter?.addFragment(
            FeatureIntroFragment.newInstance(
                imageResource,
                title,
                text
            )
        )
        return this
    }

    /**
     * Init feature intro with custom fragments.
     * @param activity the fragmentActivity for get supportFragmentManager
     * @param fragmentList a list of you own [Fragment] to use.
     */
    fun withCustom(activity: FragmentActivity, fragmentList: MutableList<Fragment>): FeatureIntro {
        initAdapter(activity)
        fromCustomFragment = 1
        fragmentList.forEach {
            adapter?.addFragment(it)
        }
        return this
    }

    /**
     * Setup the intro view and show to the user.
     */
    fun setup(listener: FeatureIntroInteractionListener) {
        this.listener = listener
        viewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(viewPager, true)
    }

    /**
     * Destroy intro instance and remove listeners.
     */
    fun destroy() {
        listener = null
        viewPager?.removeOnPageChangeListener(onPageChangeListener)
    }

    /**
     * This interface must be implemented by activities that contain this
     * view to allow an interaction in this view to be communicated to the activity
     */
    interface FeatureIntroInteractionListener {
        fun onFinishButtonClick()
    }

    private companion object {
        const val mTag = "FeatureIntro"
    }
}