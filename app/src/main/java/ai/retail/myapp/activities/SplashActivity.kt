package ai.retail.myapp.activities

import ai.retail.myapp.R
import ai.retail.myapp.databinding.ActivitySplashBinding
import ai.retail.myapp.helper.Navigator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DELAY: Long = 2000
    private lateinit var mBinding: ActivitySplashBinding
    private var mValueAnimator: ValueAnimator? = null
    private val START_VALUE = 0.5f
    private val END_VALUE = 1.0f
    private val ANIMATION_DURATION = 800
    private var mHandler: Handler? = null
    private var mFirebaseUser: FirebaseUser? = null
    private var progressStatus = 1

    private val mHandler_progress = Handler()

    private val mNavigationHandler = Runnable { if (mFirebaseUser == null) navigateToSignIn() else navigateToHome() }

    private val mProgressHandler = Runnable { mBinding.launchProgressBar.setProgress(progressStatus) }

    private fun navigateToHome() {
        Navigator.switchToHome(this)
    }

    private fun navigateToSignIn() {
        Navigator.switchToSignIn(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        val draw = resources.getDrawable(R.drawable.custome_progress_bar_tint_style, null)
        mBinding.launchProgressBar.setProgressDrawable(draw)
        initVariable()
        startLogoAnimation()
        startProgressHandler()
        startNavigationHandler()
    }

    private fun startNavigationHandler() {
        mHandler!!.postDelayed(mNavigationHandler, SPLASH_DELAY)
    }

    private fun startProgressHandler() {
        Thread {
            while (progressStatus <= 100) {
                progressStatus += 1
                // Update the progress bar and display the
                //current value in the text view
                mHandler_progress.post(mProgressHandler)
                try {
                    // Sleep for 200 milliseconds.
                    Thread.sleep(20)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun initVariable() {
        mHandler = Handler()
        mValueAnimator = ValueAnimator.ofFloat(START_VALUE, END_VALUE)
        mFirebaseUser = FirebaseAuth.getInstance().currentUser
    }

    private fun startLogoAnimation() {
        mValueAnimator!!.duration = ANIMATION_DURATION.toLong()
        mValueAnimator!!.addUpdateListener { valueAnimator: ValueAnimator ->
            val alpha = valueAnimator.animatedValue as Float
            mBinding.ivSplashLogo.setAlpha(alpha)
            mBinding.ivSplashLogo.setScaleX(START_VALUE + alpha)
            mBinding.ivSplashLogo.setScaleY(START_VALUE + alpha)
        }
        mValueAnimator!!.repeatCount = ValueAnimator.INFINITE
        mValueAnimator!!.repeatMode = ValueAnimator.REVERSE
        mValueAnimator!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mValueAnimator!!.cancel()
        mHandler!!.removeCallbacks(mNavigationHandler)
    }
}