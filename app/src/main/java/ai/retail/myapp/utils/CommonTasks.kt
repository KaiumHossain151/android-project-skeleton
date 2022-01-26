package ai.retail.myapp.utils

import ai.retail.myapp.R
import ai.retail.myapp.domain.auth.AuthResponse
import ai.retail.myapp.firebase.storage.FirebaseStorageHelper.Companion.CHILD_KYC_IMAGE
import ai.retail.myapp.firebase.storage.FirebaseStorageHelper.Companion.CHILD_POSM_IMAGE
import ai.retail.myapp.firebase.storage.FirebaseStorageHelper.Companion.CHILD_POSM_IMAGE_COMPETITION_MDO
import ai.retail.myapp.firebase.storage.FirebaseStorageHelper.Companion.CHILD_QR_IMAGE
import ai.retail.myapp.firebase.storage.FirebaseStorageHelper.Companion.CHILD_SHOP_IMAGE
import ai.retail.myapp.firebase.storage.FirebaseStorageHelper.Companion.CHILD_SHOP_IMAGE_COMPETITION_MDO
import ai.retail.myapp.firebase.storage.FirebaseStorageHelper.Companion.CHILD_SHOP_IMAGE_SURVEY_MDO
import ai.retail.myapp.firebase.storage.FirebaseStorageHelper.Companion.CHILD_SHOP_REMARKS_IMAGE_1
import ai.retail.myapp.firebase.storage.FirebaseStorageHelper.Companion.CHILD_SHOP_REMARKS_IMAGE_2
import ai.retail.myapp.utils.Constants.IMAGE_FILE_SUFFIX_REST
import ai.retail.myapp.viewmodels.datamodels.CalendarTabModel
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.sql.Timestamp
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class CommonTasks {

    companion object{
        @JvmStatic
        fun isOnline(context: Context?): Boolean {
            return if (context == null) {
                false
            } else {
                try {
                    val cm = context
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val netInfo = cm.activeNetworkInfo
                    if (netInfo != null && netInfo.isConnectedOrConnecting) {
                        return true
                    }
                    false
                } catch (e: Exception) {
                    false
                }
            }
        }

        @JvmStatic
        fun getErrorResponseBody(s: String?): AuthResponse? {
            val `object` = object : TypeToken<AuthResponse?>() {}.type
            return try {
                Gson().fromJson(s, `object`)
            } catch (jsonSyntaxException: JsonSyntaxException) {
                val temp = AuthResponse()

                temp.code = 500
                temp.errorType = "Internal Error"
                temp.message = "Json Syntax Exception"
                temp
            }
        }

        @JvmStatic
        fun showToast(context: Context?, message: String?) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }


        @JvmStatic
        fun getAlphaNumericString(n: Int): String {

            // chose a Character random from this String
            val AlphaNumericString = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    + "abcdefghijklmnopqrstuvxyz")

            // create StringBuffer size of AlphaNumericString
            val sb = StringBuilder(n)
            for (i in 0 until n) {

                // generate a random number between
                // 0 to AlphaNumericString variable length
                val index = (AlphaNumericString.length
                        * Math.random()).toInt()

                // add Character one by one in end of sb
                sb.append(AlphaNumericString[index])
            }
            return sb.toString()
        }

        @JvmStatic
        fun validatePhoneNumber(number: String?): Boolean {
            if (number == null || number.isEmpty()) return false
            val regxPattern = "^01(?:[0-9]●?){8,8}[0-9]$"
            return if (number.matches(regxPattern.toRegex())) {
                true
            } else {
                false
            }
        }


        fun isMerchant(merchant: String): Boolean {
            return Constants.TEXT_MERCHANT.equals(merchant, true)
        }

        fun isAgent(agent: String?): Boolean {
            return Constants.TEXT_AGENT.equals(agent, true)
        }

        fun isMerchantPlus(merchantPlus: String?): Boolean {
            return Constants.TEXT_MERCHANT_PLUS.equals(merchantPlus, true)
        }

        fun isSME(sme: String?): Boolean {
            return Constants.TEXT_SME.equals(sme, true)
        }

        fun isMerchantPlusLite(merchantpluslite: String?): Boolean {
            return Constants.Text_MERCHANT_PLUS_LITE.equals(merchantpluslite, true)
        }

        fun isMerchantPlusLiteB(merchantplusliteb: String?): Boolean {
            return Constants.Text_MERCHANT_PLUS_LITE_B.equals(merchantplusliteb, true)
        }


        fun getDayFromTimeStamp(timestamp: Long?): String? {
            return getYearFromTimestamp(timestamp) + "-" + getMonthFromTimestamp(timestamp) + "-" + getDateFromTimestamp(
                timestamp
            )
        }

        /**
         * Generates year from a given timestamp.
         * @param timestamp Timestamp to generates year.
         * @return Returns year as string.
         */
        fun getYearFromTimestamp(timestamp: Long?): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp!!
            return calendar[Calendar.YEAR].toString()
        }

        /**
         * Generates month from a given timestamp.
         * @param timestamp Timestamp to generates month.
         * @return Returns month as string.
         */
        fun getMonthFromTimestamp(timestamp: Long?): String {
            val format = DecimalFormat(Constants.DOUBLE_DIGIT_FORMAT)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp!!
            return format.format(java.lang.Double.valueOf((calendar[Calendar.MONTH] + 1).toDouble()))
        }

        /**
         * Generates date from a given timestamp.
         * @param timestamp Timestamp to generates date.
         * @return Returns date as string.
         */
        fun getDateFromTimestamp(timestamp: Long?): String {
            val format = DecimalFormat(Constants.DOUBLE_DIGIT_FORMAT)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp!!
            return format.format(java.lang.Double.valueOf(calendar[Calendar.DATE].toDouble()))
        }


        fun getTimeFromTimeStamp(timestamp: Long): String? {
            val dateFormat: DateFormat = SimpleDateFormat("hh:mm aa")
            val date: Date = Timestamp(timestamp)
            return dateFormat.format(date).toString()
        }


        fun getCurrentYear(): String {
            val calendar = Calendar.getInstance()
            return calendar[Calendar.YEAR].toString()
        }

        fun getCurrentMonth(): String {
            val format = DecimalFormat(Constants.DOUBLE_DIGIT_FORMAT)
            val calendar = Calendar.getInstance()
            return format.format(java.lang.Double.valueOf((calendar[Calendar.MONTH] + 1).toDouble()))
        }

        fun getCurrentDate(): String {
            val format = DecimalFormat(Constants.DOUBLE_DIGIT_FORMAT)
            val calendar = Calendar.getInstance()
            return format.format(java.lang.Double.valueOf(calendar[Calendar.DATE].toDouble()))
        }

        fun getNumbersInBengali(string: String): String? {
            val bangla_number = arrayOf('০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯')
            val eng_number = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
            var values = ""
            val character = string.toCharArray()
            for (i in character.indices) {
                var c: Char? = null
                for (j in eng_number.indices) {
                    if (character[i] == eng_number[j]) {
                        c = bangla_number[j]
                        break
                    } else {
                        c = character[i]
                    }
                }
                values = values + c
            }
            return values
        }

        fun getMonthResId_Cal_Eng(monthOfYear: Int): Int {
            when (monthOfYear) {
                1 -> return R.string.text_month_1_eng
                2 -> return R.string.text_month_2_eng
                3 -> return R.string.text_month_3_eng
                4 -> return R.string.text_month_4_eng
                5 -> return R.string.text_month_5_eng
                6 -> return R.string.text_month_6_eng
                7 -> return R.string.text_month_7_eng
                8 -> return R.string.text_month_8_eng
                9 -> return R.string.text_month_9_eng
                10 -> return R.string.text_month_10_eng
                11 -> return R.string.text_month_11_eng
                12 -> return R.string.text_month_12_eng
            }
            return 0
        }

        fun getMonthResId_Cal(monthOfYear: Int): Int {
            when (monthOfYear) {
                1 -> return R.string.text_month_1
                2 -> return R.string.text_month_2
                3 -> return R.string.text_month_3
                4 -> return R.string.text_month_4
                5 -> return R.string.text_month_5
                6 -> return R.string.text_month_6
                7 -> return R.string.text_month_7
                8 -> return R.string.text_month_8
                9 -> return R.string.text_month_9
                10 -> return R.string.text_month_10
                11 -> return R.string.text_month_11
                12 -> return R.string.text_month_12
            }
            return 0
        }

        fun getDayResId(dayOfWeek: Int): Int {
            when (dayOfWeek) {
                1 -> return R.string.text_week_day_1_day
                2 -> return R.string.text_week_day_2_day
                3 -> return R.string.text_week_day_3_day
                4 -> return R.string.text_week_day_4_day
                5 -> return R.string.text_week_day_5_day
                6 -> return R.string.text_week_day_6_day
                7 -> return R.string.text_week_day_7_day
            }
            return 0
        }

        fun getDayResIdEng(dayOfWeek: Int): Int {
            when (dayOfWeek) {
                1 -> return R.string.text_week_day_1_eng
                2 -> return R.string.text_week_day_2_eng
                3 -> return R.string.text_week_day_3_eng
                4 -> return R.string.text_week_day_4_eng
                5 -> return R.string.text_week_day_5_eng
                6 -> return R.string.text_week_day_6_eng
                7 -> return R.string.text_week_day_7_eng
            }
            return 0
        }


        fun getCalendarTitle(day: String, date: String?, month: String?, context: Context): String? {
            return day + "; " + getNumbersInBengali(date!!) + " " + context.getString(
                getMonthResId_Cal(
                    Integer.valueOf(
                        month
                    )
                )
            )
        }

        fun getCalenderHistoryTabList(context: Context): ArrayList<CalendarTabModel>? {
            val calender_tab_models: ArrayList<CalendarTabModel> = ArrayList<CalendarTabModel>()
            for (i in 0..27) {
                val calendar: Calendar = getFutureDate(-i)!!
                val temp = CalendarTabModel(
                    context.getString(getTab_Day(-i)),
                    context.getString(
                        getTab_Title(
                            -i
                        )
                    ),
                    CommonTasks.getData_DoubleDigitFormatTab(calendar[Calendar.DATE]),
                    CommonTasks.getData_DoubleDigitFormatTab(
                        calendar[Calendar.MONTH] + 1
                    ),
                    calendar[Calendar.YEAR].toString()
                )
                if (i == 0) {
                    temp.isSelected = Constants.TRUE
                }
                calender_tab_models.add(temp)
            }
            return calender_tab_models
        }


        fun getFutureDate(limit: Int): Calendar? {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, limit)
            return calendar
        }

        fun getTab_Title(limit: Int): Int {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, limit)
            return getDayResIdEng(calendar[Calendar.DAY_OF_WEEK])
        }

        fun getTab_Day(limit: Int): Int {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, limit)
            return getDayResId(calendar[Calendar.DAY_OF_WEEK])
        }

        fun getData_DoubleDigitFormatTab(value: Int): String? {
            val format = DecimalFormat(Constants.DOUBLE_DIGIT_FORMAT)
            return format.format(java.lang.Double.valueOf(value.toDouble()))
        }

        fun getHistoryListDate(day: String?, month: String?, year: String?, context: Context): String? {
            return getNumbersInBengali(day!!) + " " + context.getString(
                getMonthResId_Cal(
                    Integer.valueOf(
                        month
                    )
                )
            ) + ", " + getNumbersInBengali(year!!)
        }

        fun hideSoftKeyboard(activity: Activity?) {
            if (activity != null) {
                val focusedView = activity.currentFocus ?: return
                val inputMethodManager: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    focusedView.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }

        @JvmStatic
        fun hideSoftKeyboard(activity: Activity?, view: View?) {
            try {
                if (view != null && activity != null) {
                    val imm: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(
                        view.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
            } catch (ignored: java.lang.Exception) {
            }
        }

        @JvmStatic
        fun roundTwoDigitsAfterDecimalPoint(value: Double, places: Int): Double {
            var value = value
            require(places >= 0)
            val factor = Math.pow(10.0, places.toDouble()).toLong()
            value = value * factor
            val tmp = Math.round(value)
            return tmp.toDouble() / factor
        }


        /**
         * Replaces the filename extension.
         * @param filepath The image path need to modify.
         * @return Modifies the file path of the image.
         */
        fun modifyUrlForRESTAPI(filepath: String): String {
            return filepath.replace(Constants.IMAGE_FILE_SUFFIX, IMAGE_FILE_SUFFIX_REST)
        }

        /**
         * Adds the folder sign after folder name.
         * @param folderName
         * @return Folder name with folder sign.
         */
        fun add_folder_sign(folderName: String): String {
            return "$folderName/"
        }


        /**
         * This method is used to figure out the key of captured call images.
         * @param images Takes a Map of String image paths.
         * @return Returns the key of the image that saved in Map.
         */
        fun getCurrentKey(images: Map<String, String>): String {
            if (images.containsKey(CHILD_SHOP_IMAGE)) {
                return CHILD_SHOP_IMAGE
            } else if (images.containsKey(CHILD_SHOP_REMARKS_IMAGE_1)) {
                return CHILD_SHOP_REMARKS_IMAGE_1
            } else if (images.containsKey(CHILD_SHOP_REMARKS_IMAGE_2)) {
                return CHILD_SHOP_REMARKS_IMAGE_2
            } else if (images.containsKey(CHILD_KYC_IMAGE)) {
                return CHILD_KYC_IMAGE
            } else if (images.containsKey(CHILD_POSM_IMAGE)) {
                return CHILD_POSM_IMAGE
            } else if (images.containsKey(CHILD_QR_IMAGE)) {
                return CHILD_QR_IMAGE
            } else if (images.containsKey(CHILD_SHOP_IMAGE_COMPETITION_MDO)) {
                return CHILD_SHOP_IMAGE_COMPETITION_MDO
            } else if (images.containsKey(CHILD_POSM_IMAGE_COMPETITION_MDO)) {
                return CHILD_POSM_IMAGE_COMPETITION_MDO
            } else if (images.containsKey(CHILD_SHOP_IMAGE_SURVEY_MDO)) {
                return CHILD_SHOP_IMAGE_SURVEY_MDO
            }
            return Constants.EMPTY_STRING
        }


    }

}