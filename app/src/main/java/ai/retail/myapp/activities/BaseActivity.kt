package ai.retail.myapp.activities

import ai.retail.myapp.R
import ai.retail.myapp.helper.CrashlyticsHelper
import ai.retail.myapp.utils.CommonTasks
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    /* Primary toolbar*/
    protected var toolbar: Toolbar? = null
    protected var progressDialog: ProgressDialog? = null
    protected var mAlertDialogBuilder: AlertDialog.Builder? = null
    protected var actionBar: ActionBar? = null


    @Inject
    lateinit var mCrashlyticsHelper: CrashlyticsHelper

    private var isActivityRunning = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAlertDialogBuilder = AlertDialog.Builder(this)
        isActivityRunning = true
    }

    override fun onResume() {
        super.onResume()
        isActivityRunning = true
    }

    /**
     * Set toolbar into actionbar.
     */
    protected fun setupToolbar(id: Int) {
        if (toolbar == null) {
            toolbar = findViewById(id)
            toolbar?.setTitleTextColor(Color.BLACK)
        }
        if (toolbar != null) {
            setSupportActionBar(toolbar)
        }
        actionBar = supportActionBar
        if (actionBar != null) {
            actionBar!!.setDisplayShowHomeEnabled(true)
            actionBar!!.setDisplayShowTitleEnabled(true)
            actionBar!!.setDisplayHomeAsUpEnabled(true)
            actionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    protected fun setupToolbar(toolbar: Int, title: String?) {
        setupToolbar(toolbar)
        actionBar!!.title = title
    }

    /**
     * Initialize the loader for Child class whenever necessary.
     */
    fun initProgressLoader() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.isIndeterminate = true
        progressDialog!!.setCancelable(false)
    }

    /**
     * Initialize the loader for Child class whenever necessary.
     */
    fun initProgressLoader(isCancelable: Boolean) {
        initProgressLoader()
        progressDialog!!.setCancelable(isCancelable)
    }

    /**
     * Sets whether this dialog is cancelable with the
     */
    protected fun setProgressCancelable(isCancelable: Boolean) {
        if (progressDialog != null) {
            progressDialog!!.setCancelable(isCancelable)
        }
    }

    protected fun showTerminationAlert(isPositiveButtonActive: Boolean, isNegativeButtonActive: Boolean, positive_button_text: String?, negative_button_text: String?, title: String?, body: String?, onTerminationAcceptedListener: OnTerminationAcceptedListener) {
        mAlertDialogBuilder!!.setCancelable(false)
        mAlertDialogBuilder!!.setTitle(title)
        mAlertDialogBuilder!!.setMessage(body)
        if (isPositiveButtonActive) {
            mAlertDialogBuilder!!.setPositiveButton(positive_button_text) { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
                onTerminationAcceptedListener?.onComplete()
            }
        }
        if (isNegativeButtonActive) {
            mAlertDialogBuilder!!.setNegativeButton(negative_button_text) { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
                finish()
            }
        }
        if (isActivityRunning) mAlertDialogBuilder!!.show()
    }

    protected fun showProgressDialog() {
        showProgressDialog(getString(R.string.text_please_wait))
    }

    /**
     * Show progress dialog.
     *
     * @param message The message show in the progress dialog initially.
     */
    fun showProgressDialog(message: String?) {
        if (progressDialog == null) {
            initProgressLoader()
        }
        progressDialog!!.setMessage(message)
        if (isActivityRunning) progressDialog!!.show()
    }

    fun showProgressDialogForSelfieAttendance(message: String?) {
        if (progressDialog == null) {
            initProgressLoader()
        }
        progressDialog!!.setMessage(message)
        progressDialog!!.show()
    }

    /**
     * Cancel progress dialog.
     */
    fun cancelProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    fun showSnackBar(message: String?) {
        Snackbar.make(window.decorView, message!!, Snackbar.LENGTH_SHORT).show()
    }

    fun showSnackBar(view: View?, message: String?) {
        Snackbar.make(view!!, message!!, Snackbar.LENGTH_SHORT).show()
    }

    val isOnline: Boolean
        get() = CommonTasks.isOnline(this)

    fun setCrashlyticsUserCred(userName: String?, userEmail: String?, userId: String?) {
        mCrashlyticsHelper!!.setUserEmail(userEmail)
        mCrashlyticsHelper!!.setUserIdentifier(userId)
        mCrashlyticsHelper!!.setUserName(userName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        isActivityRunning = false
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelProgressDialog()
    }

    abstract fun initListeners()
    abstract fun initVariables()


    interface OnTerminationAcceptedListener {
        fun onComplete()
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}