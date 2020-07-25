/**
 * 
 */
package com.tykon.api.framework.service.core.constant;

/**
 * @author sachin
 *
 */
public class AppConstant {

	public static final String DATE_FORMATE = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String NOTIFICATION_CHANNEL = "Hello-Parent";
	public static final String USER_SALT = "Cghf714nNXBVfyud8kjhk6dknak3ihh675DHJGda";
	public static final Long USER_HP_CREATED_TYPE_ID = 0L;
	public static final Long USER_FREE_TYPE_ID = 1L;

	public static final String INVALID_REQUEST_DATA_KEY = "INVALID_REQUEST_DATA_KEY";

	public static final String APPLICATION_STATUS_SAVED = "S";
	public static final String APPLICATION_STATUS_CONFIRMED = "C";

	public static final String APPLICATION_TYPE_APPLY = "A";
	public static final String APPLICATION_TYPE_CALL_BACK = "C";
	public static final String APPLICATION_TYPE_INITIATED = "I";

	public final static String DEFAULT_LANGUAGE_ENGLISH = "en";

	public static final String REDIS_MAP_KEY_STATIC_DATA = "redisStaticDataMap";
	public static final String REDIS_MAP_SUB_KEY_STATIC_DATA_ALL_COUNTRIES = "redisStaticDataMapAllCountries";

	public static final String S3_STATIC_CONTENT_URL = "http://tstatic.helloparent.in/";

	public final static String USER_ROLE_ADMIN = "admin";
	public final static String USER_ROLE_SCHOOL_ADMIN = "schooladmin";
	public final static String USER_ROLE_SCHOOL = "school";

	// hello parent admin user
	public static final Long HPADMIN_USER_PARTICIPANT_TYPE_ID = 99L;
	// school admin user
	public static final Long HPSCHOOL_ADMIN_USER_PARTICIPANT_TYPE_ID = 2L;
	// school user
	public static final Long HPSCHOOL_USER_PARTICIPANT_TYPE_ID = 3L;
	// parent user
	public static final Long HPPARENT_USER_PARTICIPANT_TYPE_ID = 4L;

	public static final String COUNTRY_CODE_INDIA = "IN";

	public static final String DEFAULT_PARENT_USER_IMAGE_URL = "https://helloparent7222.blob.core.windows.net/images/6288219a.png";

	public static final String IOS_VERSION = "1.0.0";

	public static final String APP_VERSION = "1.0.0";

	public static final String IOS_BUILD_VERSION = "1.0.0";

	public static final String DEFAULT_STUDENT_BOY_IMAGE_URL = "http://tstatic.helloparent.in/uploads/school/boy_202002031013173467.jpg";
	public static final String DEFAULT_STUDENT_GIRL_IMAGE_URL = "http://tstatic.helloparent.in/uploads/school/girl_202002031012555705.jpg";

	public static final String DEFAULT_USER_IMAGE_URL = "http://tstatic.helloparent.in/uploads/school/father_image_202005180733085780.jpg";

	public static final String DATE_INDIAN_TIMEZONE = "IST";
	public static final String DATE_UTC_TIMEZONE = "UTC";

	public static final String ATTENDANCE_SERVICE = "attendance_service";

	public static final String SQS_QUEUE_NAME = "hp_msg.fifo";

	public static final String SQS_REGION = "us-east-1";

	public static final String IMAGE_HEIGHT = "height";
	public static final String IMAGE_WIDTH = "width";

	public static final String PUBLIC_ALBUM_FB_SHARE_WEB_URL = "https://helloparent.in/gallery/";
}
