package com.br.mhubaccelerator.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by marcelino on 05/07/16.
 */
public class AppUtils {
    private static final String PROP_SEP = "\\|";

    /**
     * It checks if is a valid IP address.
     *
     * @param s The IP address string.
     * @return true  If the pattern is valid.
     *         false If the parren fails.
     */
    public static Boolean isValidIp(String s) {
        String PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    /**
     * It checks if is a valid port range between 1 and 65535.
     *
     * @param s The gateway port string.
     * @return true  A valid port.
     *         false A not valid port.
     */
    public static Boolean isValidPort(String s) {
        if(!isNumber(s))
            return false;
        else {
            int port = Integer.parseInt(s);
            if(port >= 1 && port <= 65535)
                return true;
        }
        return false;
    }

    /**
     * A simple check to see if a string is a valid number before inserting
     * into the shared preferences.
     *
     * @param s The number to be checked.
     * @return true  It is a number.
     *         false It is not a number.
     */
    public static Boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * A helper class just o obtain the config file for the Shared Preferences
     * using the default values for this Shared Preferences app.
     *
     * @param c The Context of the Android system.
     * @return Returns the shared preferences with the defautl values.
     */
    private static SharedPreferences getSPrefConfig(Context c) {
        return c.getSharedPreferences(AppConfig.SHARED_PREF_FILE, Context.MODE_PRIVATE);
    }

    /**
     * It saves the IP address inside the shared preferences.
     *
     * @param c The Context of the Android system.
     * @param s The gateway IP address.
     * @return true  It returns true if is a valid IP and it was saved.
     *         false the IP is not valid and it was not saved.
     */
    public static Boolean saveIpAddress(Context c, String s) {
        if(isValidIp(s)) {
            SharedPreferences config = getSPrefConfig(c);
            SharedPreferences.Editor writer = config.edit();
            writer.putString(AppConfig.SPREF_GATEWAY_IP_ADDRESS, s);

            return writer.commit();
        }
        return false;
    }

    /**
     * It gets the IP address from the Shared Preferences.
     *
     * @param c The Context of the Android system.
     * @return String A valid IP address.
     *         null   If there is not a valid IP address or the key has no
     *                   value.
     */
    public static String getIpAddress(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        String ip = config.getString(AppConfig.SPREF_GATEWAY_IP_ADDRESS, AppConfig.DEFAULT_SDDL_IP_ADDRESS);

        if(ip.equals(""))
            return null;
        return ip;
    }

    /**
     * It saves the port of the gateway to the Shared Preferences.
     *
     * @param c The Context of the Android system.
     * @param n The gateway port.
     * @return true  If it is a valid port and it was saved.
     *         false It is not a valid port and it was not saved.
     */
    public static Boolean saveGatewayPort (Context c, Integer n) {
        if(isValidPort(n.toString())) {
            SharedPreferences config = getSPrefConfig(c);
            SharedPreferences.Editor writer = config.edit();
            writer.putString(AppConfig.SPREF_GATEWAY_PORT, n.toString());

            return writer.commit();
        }
        return false;
    }

    /**
     * It gets the gateway port.
     *
     * @param c The Context of the Android system.
     * @return Integer It returns the gateway port as an Integer.
     *         null    It has no value saved or an invalid port.
     */
    public static Integer getGatewayPort(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        String port = config.getString(AppConfig.SPREF_GATEWAY_PORT, AppConfig.DEFAULT_SDDL_PORT + "");

        if(!isNumber(port) || port.equals(""))
            return null;
        return Integer.parseInt(port);
    }

    /**
     * It generates a random UUID for the Android device.
     *
     * @return It returns the generated UUID.
     */
    public static UUID generateUuid() {
        return UUID.randomUUID();
    }

    /**
     * It creates a new random UUID to be used on the device and saves it to
     * Shared Preferences to be used again as an ID.
     *
     * @param c The Context of the Android system.
     * @return true  It was saved successfully.
     *         false It was not saved successfully.
     */
    public static Boolean createSaveUuid(Context c) {
        String strUuid = generateUuid().toString();
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putString(AppConfig.SPREF_USER_UUID, strUuid);

        return writer.commit();
    }

    /**
     * It gets the UUID saved inside the Shared Preferences.
     *
     * @param c The Context of the Android system.
     * @return UUID It returns the UUID saved.
     *         null There is no UUID save inside the Shared Preferences.
     */
    public static UUID getUuid(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        String strUuid = config.getString(AppConfig.SPREF_USER_UUID, "");

        if(strUuid.equals(""))
            return null;
        return UUID.fromString(strUuid);
    }

    /**
     * It saves the status of the Android connection service.
     *
     * @param c The Context of the Android system.
     * @param flag The status of the connection service.
     * @return true  The flag was saved successfully.
     *         false The flag was not saved successfully.
     */
    public static Boolean saveConnectionServiceRunning(Context c, Boolean flag) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putBoolean(AppConfig.SPREF_CONNECTION_SERVICE_RUNNING, flag);

        return writer.commit();
    }

    /**
     * It gets the status of the Android connection service.
     *
     * @param c The Context of the Android system.
     * @return true  The connection service is running.
     *         false The connection service is not running.
     */
    public static Boolean isConnectionServiceRunning(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        Boolean b = config.getBoolean(AppConfig.SPREF_CONNECTION_SERVICE_RUNNING, false);

        return b;
    }

    /**
     * It saves the status of the Android location service.
     *
     * @param c The Context of the Android system.
     * @param flag The status of the location service.
     * @return true  The flag was saved successfully.
     *         false The flag was not saved successfully.
     */
    public static Boolean saveLocationServiceRunning(Context c, Boolean flag) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putBoolean(AppConfig.SPREF_LOCATION_SERVICE_RUNNING, flag);

        return writer.commit();
    }

    /**
     * It gets the status of the Android location service.
     *
     * @param c The Context of the Android system.
     * @return true  The location service is running.
     *         false The location service is not running.
     */
    public static Boolean isLocationServiceRunning(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        Boolean b = config.getBoolean(AppConfig.SPREF_LOCATION_SERVICE_RUNNING, false);

        return b;
    }

    /**
     * It saves the status of the Android s2pa service.
     *
     * @param c The Context of the Android system.
     * @param flag The status of the connection service.
     * @return true  The flag was saved successfully.
     *         false The flag was not saved successfully.
     */
    public static Boolean saveS2PAServiceRunning(Context c, Boolean flag) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putBoolean(AppConfig.SPREF_S2PA_SERVICE_RUNNING, flag);

        return writer.commit();
    }

    /**
     * It gets the status of the Android s2pa service.
     *
     * @param c The Context of the Android system.
     * @return true  The connection service is running.
     *         false The connection service is not running.
     */
    public static Boolean isS2PAServiceRunning(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        Boolean b = config.getBoolean(AppConfig.SPREF_S2PA_SERVICE_RUNNING, false);

        return b;
    }

    /**
     * It saves the status of the connection.
     *
     * @param c The Context of the Android system.
     * @param flag The status of the connection.
     * @return true  The flag was saved successfully.
     *         false The flag was not saved successfully.
     */
    public static Boolean saveIsConnected(Context c, Boolean flag) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putBoolean(AppConfig.SPREF_IS_CONNECTED, flag);

        return writer.commit();
    }

    /**
     * It gets the status of the connection.
     *
     * @param c The Context of the Android system.
     * @return true  It has a live connection with the gateway.
     *         false It does not have a live connection with the gateway.
     */
    public static Boolean isConnected(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        Boolean b = config.getBoolean(AppConfig.SPREF_IS_CONNECTED, false);

        return b;
    }

    /**
     * It saves if the vehicle is moving or not.
     *
     * @param c The Context of the Android system.
     * @param flag The flag to save if is moving or not.
     * @return true  The flag was saved successfully.
     *         false The flag was not saved successfully.
     */
    public static Boolean saveIsMoving(Context c, Boolean flag) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putBoolean(AppConfig.SPREF_IS_MOVING, flag);

        return writer.commit();
    }

    /**
     * It gets if the vehicle is moving or not.
     *
     * @param c The Context of the Android system.
     * @return true  It is moving.
     *         false It is not moving.
     */
    public static Boolean isMoving(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        Boolean b = config.getBoolean(AppConfig.SPREF_IS_MOVING, false);

        return b;
    }

    /**
     * It saves the factor of the NOT MOVING value, if the vehicle is not moving
     * increase the send of the signal by the factor value.
     *
     * @param c The Context of the Android system.
     * @param f The factor value to be saved.
     * @return true  The factor was saved successfully.
     *         false The factor was not saved successfully.
     */
    public static Boolean saveNotMovingFactor(Context c, Integer f) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putInt(AppConfig.SPREF_NOT_MOVING_FACTOR, (int) f);

        return writer.commit();
    }

    /**
     * It gets the factor value used when the vehicle is not moving.
     *
     * @param c The Context of the Android system.
     * @return Integer The value of the factor.
     *         null    If there is no value saved returns null to prevent a
     *                 wrong calculation.
     */
    public static Integer getNotMovingFactor(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        Integer f = config.getInt(AppConfig.SPREF_NOT_MOVING_FACTOR, 0);

        if(f == 0)
            return null;
        return f;
    }

    /**
     * It saves the accuracy upper bound to the Shared Preferences.
     *
     * @param c The Context of the Android system.
     * @param n The new value of the accuracy upper bound.
     * @return true  The accuracy upper bound was saved successfully.
     *         false The accuracy upper bound was not saved successfully.
     */
    public static Boolean saveAccuracyUpperBound(Context c, Integer n) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putInt(AppConfig.SPREF_ACCURACY_UPPER_BOUND, (int) n);

        return writer.commit();
    }

    /**
     * It gets the accuracy upper bound, it is used to know if the point
     * acquired by the GPS, it is a good point inside the accuracy upper bound
     * range.
     *
     * @param c The Context of the Android system.
     * @return Integer The accuracy upper bound value.
     *         null    If there is no value set or the value is less than 0;
     */
    public static Integer getAccuracyUpperBound(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        Integer n = config.getInt(AppConfig.SPREF_ACCURACY_UPPER_BOUND, -1);

        if(n < 0)
            return null;
        return n;
    }

    /**
     * It saves the current interval between messages to be send to the Gateway.
     *
     * @param c The Context of the Android system.
     * @param n The value between messages to be send.
     * @return true  If the param was saved.
     *         false If the param was not saved.
     */
    public static Boolean saveCurrentSendSignalsInterval(Context c, Integer n) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putString(AppConfig.SPREF_CURRENT_SEND_SIGNALS_INTERVAL, n.toString());

        return writer.commit();
    }

    /**
     * It gets the interval between messages.
     *
     * @param c The Context of the Android system.
     * @return Integer The value.
     *         null    If it was not possible to get the value.
     */
    public static Integer getCurrentSendSignalsInterval(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        String s = config.getString(AppConfig.SPREF_CURRENT_SEND_SIGNALS_INTERVAL, "");

        if(s.equals("") || !isNumber(s))
            return null;
        return Integer.parseInt(s);
    }

    /**
     * It saves the interval between messages to be send to the Gateway.
     *
     * @param c The Context of the Android system.
     * @param n The value between messages to be send.
     * @param flag The choice between the three HIGH, MEDIUM or LOW
     * @return true  If the param was saved.
     *         false If the param was not saved.
     */
    public static Boolean saveSendSignalsInterval(Context c, Integer n, String flag) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();

		/* HIGH value */
        if(flag.equals(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_HIGH))
            writer.putString(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_HIGH, n.toString());

		/* MEDIUM value */
        else if(flag.equals(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_MEDIUM))
            writer.putString(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_MEDIUM, n.toString());

		/* LOW value */
        else if(flag.equals(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_LOW))
            writer.putString(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_LOW, n.toString());

        return writer.commit();
    }

    /**
     * It gets the interval values between messages from the choices HIGH, MEDIUM or LOW.
     *
     * @param c The Context of the Android system.
     * @param flag The choice between the three HIGH, MEDIUM or LOW
     * @return Integer The value.
     *         null    If it was not possible to get the value.
     */
    public static Integer getSendSignalsInterval(Context c, String flag) {
        SharedPreferences config = getSPrefConfig(c);
        String s = "";

		/* HIGH value */
        if(flag.equals(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_HIGH))
            s = config.getString(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_HIGH, AppConfig.DEFAULT_SEND_SIGNALS_INTERVAL_HIGH + "");

		/* MEDIUM value */
        else if(flag.equals(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_MEDIUM))
            s = config.getString(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_MEDIUM, AppConfig.DEFAULT_SEND_SIGNALS_INTERVAL_MEDIUM + "");

		/* LOW value */
        else if(flag.equals(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_LOW))
            s = config.getString(AppConfig.SPREF_SEND_SIGNALS_INTERVAL_LOW, AppConfig.DEFAULT_SEND_SIGNALS_INTERVAL_LOW + "");

        if(s.equals("") || !isNumber(s))
            return null;
        return Integer.parseInt(s);
    }

    /**
     * It saves the location min interval.
     *
     * @param c The Context of the Android system.
     * @param n The new min value.
     * @return true If it was saved.
     *         false If it was not saved.
     */
    public static Boolean saveCurrentLocationInterval(Context c, Integer n) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putString(AppConfig.SPREF_CURRENT_LOCATION_INTERVAL, n.toString());

        return writer.commit();
    }

    /**
     * It gets the location min intervar value.
     *
     * @param c The Context of the Android system.
     * @return Integer The value.
     *         null    If it was not possible to get the value.
     */
    public static Integer getCurrentLocationInterval(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        String s = config.getString(AppConfig.SPREF_CURRENT_LOCATION_INTERVAL, "");

        if(s.equals("") || !isNumber(s))
            return null;
        return Integer.parseInt(s);
    }

    /**
     * It saves the scan min interval.
     *
     * @param c The Context of the Android system.
     * @param n The new min value.
     * @return true If it was saved.
     *         false If it was not saved.
     */
    public static Boolean saveCurrentScanInterval(Context c, Integer n) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putString(AppConfig.SPREF_CURRENT_SCAN_INTERVAL, n.toString());

        return writer.commit();
    }

    /**
     * It gets the current scan interval value.
     *
     * @param c The Context of the Android system.
     * @return Integer The value.
     *         null    If it was not possible to get the value.
     */
    public static Integer getCurrentScanInterval(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        String s = config.getString(AppConfig.SPREF_CURRENT_SCAN_INTERVAL, "");

        if(s.equals("") || !isNumber(s))
            return null;
        return Integer.parseInt(s);
    }

    /**
     * It saves the location min interval for the three values, HIGH, MEDIUM or LOW.
     *
     * @param c The Context of the Android system.
     * @param n The new min value.
     * @param flag The choice between the three HIGH, MEDIUM or LOW
     * @return true If it was saved.
     *         false If it was not saved.
     */
    public static Boolean saveLocationInterval(Context c, Integer n, String flag) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();

		/* HIGH value */
        if(flag.equals(AppConfig.SPREF_LOCATION_INTERVAL_HIGH))
            writer.putString(AppConfig.SPREF_LOCATION_INTERVAL_HIGH, n.toString());

		/* MEDIUM value */
        else if(flag.equals(AppConfig.SPREF_LOCATION_INTERVAL_MEDIUM))
            writer.putString(AppConfig.SPREF_LOCATION_INTERVAL_MEDIUM, n.toString());

		/* LOW value */
        else if(flag.equals(AppConfig.SPREF_LOCATION_INTERVAL_LOW))
            writer.putString(AppConfig.SPREF_LOCATION_INTERVAL_LOW, n.toString());

        return writer.commit();
    }

    /**
     * It gets the location min interval value for the three options HIGH, MEDIUM or LOW.
     *
     * @param c The Context of the Android system.
     * @param flag The choice between the three HIGH, MEDIUM or LOW
     * @return Integer The value.
     *         null    If it was not possible to get the value.
     */
    public static Integer getLocationInterval(Context c, String flag) {
        SharedPreferences config = getSPrefConfig(c);
        String s = "";

		/* HIGH value*/
        if(flag.equals(AppConfig.SPREF_LOCATION_INTERVAL_HIGH))
            s = config.getString(AppConfig.SPREF_LOCATION_INTERVAL_HIGH, AppConfig.DEFAULT_LOCATION_INTERVAL_HIGH + "");

		/* MEDIUM value */
        else if(flag.equals(AppConfig.SPREF_LOCATION_INTERVAL_MEDIUM))
            s = config.getString(AppConfig.SPREF_LOCATION_INTERVAL_MEDIUM, AppConfig.DEFAULT_LOCATION_INTERVAL_MEDIUM + "");

		/* LOW value */
        else if(flag.equals(AppConfig.SPREF_LOCATION_INTERVAL_LOW))
            s = config.getString(AppConfig.SPREF_LOCATION_INTERVAL_LOW, AppConfig.DEFAULT_LOCATION_INTERVAL_LOW + "");

        if(s.equals("") || !isNumber(s))
            return null;
        return Integer.parseInt(s);
    }

    /**
     * It saves the scan min interval for the three values, HIGH, MEDIUM or LOW.
     *
     * @param c The Context of the Android system.
     * @param n The new min value.
     * @param flag The choice between the three HIGH, MEDIUM or LOW
     * @return true If it was saved.
     *         false If it was not saved.
     */
    public static Boolean saveScanInterval(Context c, Integer n, String flag) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();

		/* HIGH value */
        if(flag.equals(AppConfig.SPREF_SCAN_INTERVAL_HIGH))
            writer.putString(AppConfig.SPREF_SCAN_INTERVAL_HIGH, n.toString());

		/* MEDIUM value */
        else if(flag.equals(AppConfig.SPREF_SCAN_INTERVAL_MEDIUM))
            writer.putString(AppConfig.SPREF_SCAN_INTERVAL_MEDIUM, n.toString());

		/* LOW value */
        else if(flag.equals(AppConfig.SPREF_SCAN_INTERVAL_LOW))
            writer.putString(AppConfig.SPREF_SCAN_INTERVAL_LOW, n.toString());

        return writer.commit();
    }

    /**
     * It gets the scan min interval value for the three options HIGH, MEDIUM or LOW.
     *
     * @param c The Context of the Android system.
     * @param flag The choice between the three HIGH, MEDIUM or LOW
     * @return Integer The value.
     *         null    If it was not possible to get the value.
     */
    public static Integer getScanInterval(Context c, String flag) {
        SharedPreferences config = getSPrefConfig(c);
        String s = "";

		/* HIGH value*/
        if(flag.equals(AppConfig.SPREF_SCAN_INTERVAL_HIGH))
            s = config.getString(AppConfig.SPREF_SCAN_INTERVAL_HIGH, AppConfig.DEFAULT_SCAN_INTERVAL_HIGH + "");

		/* MEDIUM value */
        else if(flag.equals(AppConfig.SPREF_SCAN_INTERVAL_MEDIUM))
            s = config.getString(AppConfig.SPREF_SCAN_INTERVAL_MEDIUM, AppConfig.DEFAULT_SCAN_INTERVAL_MEDIUM + "");

		/* LOW value */
        else if(flag.equals(AppConfig.SPREF_SCAN_INTERVAL_LOW))
            s = config.getString(AppConfig.SPREF_SCAN_INTERVAL_LOW, AppConfig.DEFAULT_SCAN_INTERVAL_LOW + "");

        if(s.equals("") || !isNumber(s))
            return null;
        return Integer.parseInt(s);
    }

    /**
     * It gets the unread messages count.
     *
     */
    public static Integer getUnreadMessages(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        Integer n = config.getInt(AppConfig.SPREF_UNREAD_MESSAGES, -1);

        if(n <= -1)
            return null;
        return n;
    }

    /**
     * It adds one to the unread messages
     */
    public static Boolean incUnreadMessages(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        Integer current = config.getInt(AppConfig.SPREF_UNREAD_MESSAGES, 0);
        writer.putInt(AppConfig.SPREF_UNREAD_MESSAGES, current + 1);

        return writer.commit();
    }

    /**
     * It reset the unread messages counter to zero.
     */
    public static Boolean resetUnreadMessages(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putInt(AppConfig.SPREF_UNREAD_MESSAGES, AppConfig.DEFAULT_UNREAD_MESSAGES);

        return writer.commit();
    }

    /**
     * It saves the auto connect to mobile objects state.
     *
     * @param c The Context of the Android system.
     * @param n The new auto connect value.
     * @return true If it was saved.
     *         false If it was not saved.
     */
    public static Boolean saveCurrentAutoconnectMO(Context c, Boolean n) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putBoolean(AppConfig.SPREF_AUTOCONNECT_MO, n);

        return writer.commit();
    }

    /**
     * It gets the current auto connect to mobile objects state.
     *
     * @param c The Context of the Android system.
     * @return Boolean The value.
     */
    public static Boolean getCurrentAutoconnectMO(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        Boolean s = config.getBoolean(AppConfig.SPREF_AUTOCONNECT_MO, AppConfig.DEFAULT_AUTOCONNECT_MO);

        return s;
    }

    /**
     * It saves the current minimum range accepted to connect to mobile objects.
     *
     * @param c The Context of the Android system.
     * @param n The new minimum signal allowed.
     * @return true If it was saved.
     *         false If it was not saved.
     */
    public static Boolean saveCurrentSignalAllowedMO(Context c, Integer n) {
        SharedPreferences config = getSPrefConfig(c);
        SharedPreferences.Editor writer = config.edit();
        writer.putInt(AppConfig.SPREF_SINGAL_RANGE_MO, n);

        return writer.commit();
    }

    /**
     * It gets the current minimum range accepted to connect to mobile objects.
     *
     * @param c The Context of the Android system.
     * @return The value.
     */
    public static Integer getCurrentSignalAllowedMO(Context c) {
        SharedPreferences config = getSPrefConfig(c);
        Integer s = config.getInt(AppConfig.SPREF_SINGAL_RANGE_MO, AppConfig.DEFAULT_SIGNAL_RANGE_MO);

        return s;
    }

    /**
     *
     * @param serviceName
     * @param property
     * @return
     */
    public static boolean isInRoute(String serviceName, String property) {
        String[] parts = property.split(PROP_SEP);

        for(String service : parts) {
            if(service.equals(serviceName))
                return true;
        }

        return false;
    }

    /**
     * Logger for the service, depending on the flag DEBUG
     * @param type The char type of the log
     * @param TAG The String used to know to which class the log belongs
     * @param text The String to output on the log
     */
    public static void logger(char type, String TAG, String text) {
        if(text == null)
            Log.e(TAG, "NULL Message");

        if(AppConfig.DEBUG) {
            switch(type) {
                case 'i': // Information
                    Log.i(TAG, text);
                    break;

                case 'w': // Warning
                    Log.w(TAG, text);
                    break;

                case 'e': // Error
                    Log.e(TAG, text);
                    break;

                case 'd': // Debug
                    Log.d(TAG, text);
                    break;

                default:
                    Log.e(TAG, text);
                    break;
            }
        }
    }
}

