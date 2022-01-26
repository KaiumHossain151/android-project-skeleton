package ai.retail.myapp.utils;

public interface Constants {

    String APP_JSON = "application/json";
    String APP_URLENCODED = "application/x-www-form-urlencoded";
    String CALL_TAG_EN_REMOVAL = "removal";
    String CALL_TAG_EN_INTRODUCTION = "introduction";
    String CAMPAIGN_RELATED = "ক্যাম্পেইন সম্পর্কিত";
    String slot_second_completed = "second_slot";
    String slot_first_completed = "first_slot";
    String summary_shown = "summary_shown";
    String BMCC_Re_Entry = "BMCC_Re_Entry";
    String OnlineMerchant = "9999";
    String INVALID ="INVALID";
    String VALID = "VALID";
    String developing = "developing";
    String production = "production";
    long MAX_TRANSFER_RETRY_MILLIS = 20000;

    final class EncodingTypes{
        public static final String UTF_8 = "UTF-8";
        public static final String ENC_UTF8 = "UTF-8";
        public static final String ENC_ISO_8859_1 = "iso-8859-1";
        public static final String ENC_ISO_8859_2 = "iso-8859-2";
    }

    final class HTTP {
        public static final long ERROR_404 = 404;
        public static final long ERROR_403 = 403;
    }

    class Text{
        public static final String DOUBLE_LINE_SEP = "\n\n";
        public static final String SINGLE_LINE_SEP = "\n";
    }

    String EMPTY_STRING = "";
    float LOCATION_ACCURACY_THRESHOLD = 20f;
    String IMAGE_FILE_SUFFIX = ".jpg";
    String IMAGE_FILE_SUFFIX_REST = "_rest.jpg";
    String IMAGE_FILE_TXT_SUFFIX = ".txt";
    long NANO_SECOND = 1000000000;
    long LOCATION_UPDATE_TIME_THRESHOLD = 30; //second
    String DOUBLE_DIGIT_FORMAT = "00";
    long DEFAULT_TIME = 0;
    float OUTLET_LOCATION_VALIDATION_THRESHOLD = 30.0f;
    String DEFAULT_BMCC_CODE = "4000";
    String DEFAULT_BMCC_CODE_NOTFOUND_CLOSE = "0000";
    String TEXT_AGENT = "Agent";
    String TEXT_MERCHANT = "Merchant";
    String TEXT_MERCHANT_PLUS = "Merchant Plus";
    String TEXT_SME = "SME";
    String Text_MERCHANT_PLUS_LITE = "Merchant Plus Lite A";
    String Text_MERCHANT_PLUS_LITE_B = "Merchant Plus Lite B";
    double DEFAULT_DOUBLE = 0.0;
    long DEFAULT_LONG = 0l;
    String ANSWER_NO = "no";
    String ANSWER_YES = "yes";
    String SERVER_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    int DEFAULT_INT = 0;
    int PageSize = 15;
    String URL_PREFIX = "http";
    CharSequence STRICT = "strict";
    String SPACE = " ";
    long ONE_DAY_MILL_SECOND = 24 * 60 * 60 * 1000;
    long TWENTY_MINUTE = 60 * 60 * 1000;
    String TAG_START_DATE_PICKER = "start_date_picker";
    String TAG_RETURN_DATE_PICKER = "return_date_picker";
    String TAG_TIMELINE = "Timeline";
    String NOT_AVAILABLE = "N/A";
    long FIVE_MINUTE = 5 * 60 * 1000;
    int START_INDEX = 0;
    String COMMON_ERROR_MESSAGE = "Error occured. try again";
    String COMMON_NOT_FOUND = "NotFound";
    // initialize qr variable as contant
    int MAX_MONTH_IN_YEAR = 11;
    String INPUT_TEXT_VALUE = "";
    boolean QR_YES = true;
    boolean QR_NO = false;
//    String NOT_APPLICABLE = "not applicable";
    String NOT_APPLICABLE = "N/A";
    String PARTIAL = "Partial";
    String ANSWER_NOTFOUND_CLOSE= "Not applicable";
    String NOTAPPLICABLE_FOR_REPORTER= "Not Applicable";
    boolean IS_ONLINE = true;
    boolean IS_OFFLINE = false;
    String regxPattern = "^01(?:[0-9]●?){8,8}[0-9]$";
    String regxPattern_2 = "^8801(?:[0-9]●?){8,8}[0-9]$";
    String AUTO_SPOT_VALIDATION_DEFAULT = "[\"00000\"]";
    String regxPatternForCompetitionNumberValidation = "^01(?:[0-9]●?){8,10}[0-9]$";

    boolean TRUE = true;
    boolean FALSE = false;
    String DefaultAttendance = "{\"attendance\":0,\"selfieAttendance\":\"test\"}";
    String hostname = "207.254.208.199";
    int port = 3128;

    String count_ = "count";
    String page_ = "page";
    String limit_ = "limit";
    String data_ = "data";
    String All = "all";
    String waiting = "waiting";
    String processed = "processed";
    String approved = "approved";
    String rejected = "rejected";
    String resolved = "resolved";
    String Bulk_Notification = "bulk notification";
    String New_Issue = "new issue";

    String competition = "competition_image";
    String competition_upload = "competition_upload";

//    String reporter = "Reporter";
//    String OUTLET_NUMBER = "Outlet Number";
//    String OUTLET_NAME = "Outlet Name";
//    String OUTLET_AREA = "Outlet Area";
//    String COMPETITOR_COMPANY = "Competitor Company";
//    String COMPETITORS_ACTIVITY = "Competitors Activity";
//    String competition_images = "competition image count";
//    String reporter_name = "Reporter Name";
//    String synced_outlet_count = "Number of synced outlets";
//    String ad_hoc_outlet_count = "Number of ad hoc outlets";
//    String draftOutletCalls = "draftOutletCalls";
//    String Ad_Hoc_Task_Completion = "Ad Hoc Task Completion";
//    String Sync_Outlet_Info = "Sync Outlet Info";
//    String TOTAL_CALLS = "Total Calls";
//    String OPEN_OUTLETS = "Open Outlets";
//    String CLOSED_OUTLETS = "Closed Outlets";
//    String CALL_TO_OUTLETS = "Call to Outlets";
//    String COMPETITION_UPDATE = "competition update";
//    String NOT_FOUND_OUTLETS = "Not found Outlets";
//    String Sync_Ad_Hoc_Outlet = "Ad Hoc Tasks";

    String IS_CAMPAIGN = "campaign";
    String IS_AD_HOC = "ad_hoc";
    String IS_VALIDATION = "validation";
    String IS_REGULAR = "regular";
    String CALL_TYPE_CAMPAIGN = "campaign";
    String CALL_TYPE_AD_HOC = "ad_hoc";
    String CALL_TYPE_REGULAR = "regular";
    String CALL_TYPE_VALIDATION = "validation";
    String CALL_TYPE_NEW_FOUND_OUTLET = "specific_outlet_call";

    String CALL_TYPE_CAMPAIGN_TRUE = "campaign_true";
    String CALL_TYPE_REGULAR_TRUE = "regular_true";
    String CALL_TYPE_VALIDATION_TRUE = "validation_true";
    String CALL_TYPE_AD_HOC_TRUE = "ad_hoc_true";
    String CALL_TYPE_COMPLETED_TRUE = "completed";
    String SUPERVISION_RATING = "ratings";
    String SUPERVISION_REPORT = "questions";
    String OPTIONAL = "optional";
    int STATUS_DATA_UPLOADED = 2;
    String AUTO_SPOT_CODE = "A000";
    String RATING_1 = "1";
    String RATING_2 = "2";
    String RATING_3 = "3";
    String RATING_4 = "4";
    String RATING_5 = "5";

    String MERCHADISER = "merchandiser";
    String SUPERVISOR = "supervisor";

    String Language = "English";
    String Language_bangla = "বাংলা";
    String TASKTYPE_MANDATORY = "mandatory";

//    String AttendanceTimeStart_AM = "9:00:00";
//    String AttendanceTimeEnd_AM = "11:30:00";
//
//    String AttendanceTimeStart_PM = "14:00:00";
//    String AttendanceTimeEnd_PM = "15:00:00";

    String AttendanceTimeFormat = "HH:mm:ss";
    String AttendanceDateFormat = "yyyy-MM-dd";

    String SCAN_FAILED = "Scan Failed";
    String APP_VERSION_DEV = "_dev";
    String CALL_TYPE = "call_type";
    String OUTLET_TYPE = "outlet_type";
    String APP_VERSION = "app version";

    String bng_1 = "১";
    String bng_2 = "২";
    String bng_3 = "৩";
    String bng_4 = "৪";
    String bng_5 = "৫";
    String bng_6 = "৬";
    String bng_7 = "৭";
    String bng_8 = "৮";
    String bng_9 = "৯";
    String bng_10 = "১০";
    String bng_11 = "১১";
    String bng_12 = "১২";
    String bng_13 = "১৩";
    String bng_14 = "১৪";
    String bng_15 = "১৫";

    String LANDMARK = "landmark";
    String LANDMARK_CALL = "landmark_call";



    String transactionId_regex_outlet_number = "01(?:[0-9]●?){8,8}[0-9]";
    String transactionId_transaction_feedback = "bKash";
    String transactionId_TrxID = "TrxID";
    String transactionId_validation_regex = "[A-Z0-9]{10,10}$";



    String ROUTE_REALIGNMENT_FORM = "https://docs.google.com/forms/d/1iAf38OO3Ql55kRFdy-iKriYujo7MGvIMYr3T3nI6VXQ/viewform?edit_requested=true";


    String text_week_day_1 = "রবিবার";
    String text_week_day_2 = "সোমবার";
    String text_week_day_3 = "মঙ্গলবার";
    String text_week_day_4 = "বুধবার";
    String text_week_day_5 = "বৃহস্পতিবার";
    String text_week_day_6 = "শুক্রবার";
    String text_week_day_7 = "শনিবার";

    String day1 = "SUN";
    String day2 = "MON";
    String day3 = "TUE";
    String day4 = "WED";
    String day5 = "THU";
    String day6 = "FRI";
    String day7 = "SAT";
    int INT_ONE = 1;
    int INT_TWO = 2;
    int INT_THREE = 3;
    int INT_FOUR = 4;
    int INT_FIVE = 5;
    int INT_SIX = 6;
    int INT_SEVEN = 7;
    int INT_EIGHT = 8;
    int INT_NINE = 9;

    int ROUTE_FREQUENCY_TWICE_IN_A_MONTH = 2;
    int ROUTE_FREQUENCY_FOUR_TIMES_IN_A_MONTH = 4;

    String ValidationError = "ValidationError";
    String NotFound = "NotFound";
    String DEV_API = "dev.";
    String Transfer = "পরিবর্তিত";
    String Reject = "Reject";
    int Limit_One_Day = 15;
    int Limit_One_Month = 30;

    String ROUTE_TRANSFER_CONFIG_DEFAULT = "{\"base_url\": \"http://dev.api.retaildata.xyz/\",\"url_extension\": \"v1/route-plan/\",\"route_transfer_enable\": false,\"base_url_route_realignment\": \"http://35.221.89.148:8080/route-realignment\",\"route_realignment_enable\": false}";
    String ROUTE_TRANSFER_REQUEST_FAILED = "{\"error\": {\"type\": \"ValidationError\",\"message\": \"আবেদন পাঠাতে ব্যার্থ হয়েছে\",\"detail\": null}}";

    String ROUTE_TRANSFER_REQUEST_FAILED_MESSAGE = "আবেদন পাঠাতে ব্যার্থ হয়েছে";
    CharSequence NOT_FOUND = "Not Found";

    int MAX_INDEX_CAMPAIGN = 13;
    int MAX_INDEX_REGULAR = 13;
    int MAX_INDEX_VALIDATION = 15;
    int MAX_INDEX_NOT_FOUND_CLOSED = 4;

    String QR_QUESTION = "qr_question";
    String CAMPAIGN_QUESTION = "campaign_question";
    String COMPLAIN_QUESTION = "complain_question";
    String DYNAMIC_TASK_LIST = "dynamic_task_list";
    String NEAREST_OUTLET = "nearest_outlet";
    String SPOT_CODE = "spot_code";
    String FIRST_FRAGMENT = "first_fragment";

    String PhoneNumberPrefix = "%2B";

    String ImageKey = "UkXp2s5v8x/A?D(G";
    String ImageSpecKeys= "dSgUkXp2s5v8y/B?";

    String ACTIVITY_CODE_VERIFICATION = "CodeVerificationActivity";
    String ACTIVITY_SPLASH = "SplashActivity";

    String agent = "agent";

    String ROUTE_TRANSFER_REJECTION_URL = "https://analytics-server.retaildata.xyz/bkash-biponon/transfers/api/v1/";
    String ROUTE_REALIGNMENT = "http://webview.retaildata.xyz/route-realignment";
    String BaseUrl = "https://analytics.intelligentmachines.xyz/";

    long Encryption_Delay = 200;

    CharSequence IMAGE_NOT_FOUND = "Image not found";
    CharSequence IMAGE_UPLOAD_FAILED = "Image upload failed";
    long MAX_RETRY_FIREBASE_STORAGE = 30000;
    String close_outlet = "close_outlet";
    String notfound_outlet = "notfound_outlet";
    String notfound_outlet_agent = "notfound_outlet_agent";
    String notfound_outlet_merchant = "notfound_outlet_merchant";
    String close_outlet_agent = "close_outlet_agent";
    String close_outlet_merchant = "close_outlet_merchant";
    String baseUrlPrefix = "bkashbiponon.appspot.com__";

    String FAILED = "failed";
    String PASSED = "passed";
    String SPECIAL_VALIDATION = "special_validation";
    String SAVED_SPECIFIC_CALL = "SAVED_SPECIFIC_CALL";
    String COMMA_SEPERATOR = ", ";
    String USER_PHONE_NUMBER = "phoneNumber";
    String OUTLET_STATUS = "outlet_status";
    String CLOSED = "closed";
    String DONE = "done";
    String LeaveApproved = "LeaveApproved";
    String LeaveRequest = "LeaveRequest" ;
    String LeaveApprovedSuccess = "LeaveApprovedSuccess";
    String LeaveRequestSuccess = "LeaveRequestSuccess";
    String LeaveSupervision = "LeaveSupervision";
    String TodayVacation = "TodayVacation";

    String StorageBucketNimontron = "https://firebasestorage.googleapis.com/v0/b/bkashnimontron.appspot.com/o/";
    String StorageBucketCF  = "https://storage.googleapis.com/bkashbiponon.appspot.com/";
    String StorageBucket  = "https://firebasestorage.googleapis.com/v0/b/bkashbiponon.appspot.com/o/";
    int THREE_H = 300;
    String QUESTION_MARKS = "?";
    String FoundNewPage = "FoundNewPage";
    String TESTER = "TESTER";
    String OUTLET_INFO_UPDATE = "OUTLET_INFO_UPDATE";
    String COMPETITION_UPDATE = "COMPETITION_UPDATE";
    String competition_upload_success = "competition_upload_success";

    int SWIPE_THRESHOLD = 200;
    int SWIPE_VELOCITY_THRESHOLD = 150;

    String UPLOAD_SUCCESS = "UPLOAD_SUCCESS";
    String STORED_OFFLINE = "STORED_OFFLINE";

    String PRIORITY_HIGH = "high";
    String PRIORITY_MEDIUM = "medium";
    String PRIORITY_LOW = "low";

    String PRIORITY_HIGH_BN = "অত্যন্ত জরুরি";
    String PRIORITY_MEDIUM_BN = "জরুরি";
    String PRIORITY_LOW_BN = "কম জরুরি";
    String DSO = "DSO";
    int Notification_limit = 50;
    String posm_feedback_complain = "posm_feedback_complain";
    String numberPrefix = "+88";
    String pending = "pending";

    String SURVEY_QUESTION_2 = "question_2";
    String SURVEY_QUESTION_1 = "question_1";
    String approved_or_rejected = "approved_or_rejected";
    String posm_report = "posm_report";
    String merchant = "merchant";
    String posm_resolved = "posm_resolved";
    int limit_size = 10000;
    String POSM_Supervision = "POSM Supervision";
    String POSM_Solution = "POSM Solution";

    String tag_not_in_merchandiser_under = "tag_not_in_merchandiser_under";
    String tag_already_issue_created = "tag_already_issue_created";

    String Cellular = "Cellular";
    String Wifi = "Wifi";


    int SIZE_4 = 4;
    int SIZE_8 = 8;
    int SIZE_10 = 10;
    int SIZE_12 = 12;
    int SIZE_14 = 14;
    int SIZE_16 = 16;
    int SIZE_18 = 18;
    int SIZE_20 = 20;
    int SIZE_22 = 22;
    int SIZE_24 = 24;
    int SIZE_40 = 40;
    int SIZE_64 = 64;


    String checkbox = "checkbox";
    String radio = "radio_button";
    String button = "button";

    String NETWORK_STATUS= "NETWORK_STATUS";

    String Error_Occurred = "Error Occurred";
    String Error_Body = "Error:%s, message:%s";

}
