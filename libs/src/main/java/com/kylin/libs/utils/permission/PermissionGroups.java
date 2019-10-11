package com.kylin.libs.utils.permission;

/**
 * 权限组
 *
 * @author Kylin
 * @date 2017/12/12.
 */
public class PermissionGroups {
    public static final int CALENDAR = 1;
    public static final int CAMERA = 2;
    public static final int CONTACTS = 3;
    public static final int LOCATION = 4;
    public static final int MICROPHONE = 5;
    public static final int PHONE = 6;
    public static final int SENSORS = 7;
    public static final int SMS = 8;
    public static final int STORAGE = 9;

    public static final String PREMISION_READ_CALENDAR = "android.permission.READ_CALENDAR";
    public static final String PREMISION_WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";

    public static final String PREMISION_CAMERA = "android.permission.CAMERA";

    public static final String PREMISION_READ_CONTACTS = "android.permission.READ_CONTACTS";
    public static final String PREMISION_WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";
    public static final String PREMISION_GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";

    public static final String PREMISION_ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    public static final String PREMISION_ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";

    public static final String PREMISION_RECORD_AUDIO = "android.permission.RECORD_AUDIO";

    public static final String PREMISION_READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    public static final String PREMISION_CALL_PHONE = "android.permission.CALL_PHONE";
    public static final String PREMISION_READ_CALL_LOG = "android.permission.READ_CALL_LOG";
    public static final String PREMISION_WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";
    public static final String PREMISION_ADD_VOICEMAIL = "android.permission.ADD_VOICEMAIL";
    public static final String PREMISION_USE_SIP = "android.permission.USE_SIP";
    public static final String PREMISION_PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS";

    public static final String PREMISION_BODY_SENSORS = "android.permission.BODY_SENSORS";

    public static final String PREMISION_SEND_SMS = "android.permission.SEND_SMS";
    public static final String PREMISION_RECEIVE_SMS = "android.permission.RECEIVE_SMS";
    public static final String PREMISION_READ_SMS = "android.permission.READ_SMS";
    public static final String PREMISION_RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";
    public static final String PREMISION_RECEIVE_MMS = "android.permission.RECEIVE_MMS";

    public static final String PREMISION_READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static final String PREMISION_WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";


    public static int getCheckType(String permission) {
        if ((permission.equals(PREMISION_READ_CALENDAR))
                || (permission.equals(PREMISION_WRITE_CALENDAR))) {
            return CALENDAR;
        }
        if (permission.equals(PREMISION_CAMERA)) {
            return CAMERA;
        }
        if ((permission.equals(PREMISION_READ_CONTACTS))
                || (permission.equals(PREMISION_WRITE_CONTACTS))
                || (permission.equals(PREMISION_GET_ACCOUNTS))) {
            return CONTACTS;
        }
        if ((permission.equals(PREMISION_ACCESS_FINE_LOCATION))
                || (permission
                .equals(PREMISION_ACCESS_COARSE_LOCATION))) {
            return LOCATION;
        }
        if (permission.equals(PREMISION_RECORD_AUDIO)) {
            return MICROPHONE;
        }
        if ((permission.equals(PREMISION_READ_PHONE_STATE))
                || (permission.equals(PREMISION_CALL_PHONE))
                || (permission.equals(PREMISION_READ_CALL_LOG))
                || (permission.equals(PREMISION_WRITE_CALL_LOG))
                || (permission.equals(PREMISION_ADD_VOICEMAIL))
                || (permission.equals(PREMISION_USE_SIP))
                || (permission
                .equals(PREMISION_PROCESS_OUTGOING_CALLS))) {
            return PHONE;
        }
        if (permission.equals(PREMISION_BODY_SENSORS)) {
            return SENSORS;
        }
        if ((permission.equals(PREMISION_SEND_SMS))
                || (permission.equals(PREMISION_RECEIVE_SMS))
                || (permission.equals(PREMISION_READ_SMS))
                || (permission.equals(PREMISION_RECEIVE_WAP_PUSH))
                || (permission.equals(PREMISION_RECEIVE_MMS))) {
            return SMS;
        }
        if ((permission.equals(PREMISION_READ_EXTERNAL_STORAGE))
                || (permission.equals(PREMISION_WRITE_EXTERNAL_STORAGE))) {
            return STORAGE;
        }

        return -1;
    }
}
