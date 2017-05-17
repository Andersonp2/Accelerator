package com.br.mhubaccelerator.widget;

/**
 * Created by marcelino on 05/07/16.
 */
public class AppConfig {
    // DEBUG flag
    public static final boolean DEBUG = true;

    // ID of the shared preferences file.
    public static final String SHARED_PREF_FILE = "InfopaeSharedPref";

    /**
     * Keys used with the Shared Preferences (SP) and default values.
     * {{ ======================================================================
     */

	/* Ip Address of the SDDL gateway
	 * type -- String
	 */
    public static final String SPREF_GATEWAY_IP_ADDRESS = "SPGatewayIpAddress";

    /* Port of the SDDL gateway
     * type -- String
     */
    public static final String SPREF_GATEWAY_PORT = "SPGatewayPort";

    /* The UUID of the user-device
     * type -- String
     */
    public static final String SPREF_USER_UUID = "SPUserUuid";

    /* The current interval used by the connection service to send messages.
     * type -- Integer
     */
    public static final String SPREF_CURRENT_SEND_SIGNALS_INTERVAL = "SPCurrentSendSignalsInterval";

    /* The interval values used by the connection service, this parameters are
     * set manually from the application configuration.
     */
    public static final String SPREF_SEND_SIGNALS_INTERVAL_HIGH   = "SPSendSignalsIntervalHigh";
    public static final String SPREF_SEND_SIGNALS_INTERVAL_MEDIUM = "SPSendSignalsIntervalMedium";
    public static final String SPREF_SEND_SIGNALS_INTERVAL_LOW    = "SPSendSignalsIntervalLow";

    /* The current interval used by the mRouter to start a scan for M-Objects.
     * type -- Integer
     */
    public static final String SPREF_CURRENT_SCAN_INTERVAL = "SPCurrentScanInterval";

    /* The current interval used by the location service to send new locations.
     * type -- Integer
     */
    public static final String SPREF_CURRENT_LOCATION_INTERVAL = "SPCurrentLocationInterval";

    /* The interval values used by the location service, this parameters are
     * set manually from the application configuration.
     * type -- Integer
     */
    public static final String SPREF_LOCATION_INTERVAL_HIGH   = "SPLocationIntervalHigh";
    public static final String SPREF_LOCATION_INTERVAL_MEDIUM = "SPLocationIntervalMedium";
    public static final String SPREF_LOCATION_INTERVAL_LOW    = "SPLocationIntervalLow";

    /* The interval values used by the location service, this parameters are
     * set manually from the application configuration.
     * type -- Integer
     */
    public static final String SPREF_SCAN_INTERVAL_HIGH   = "SPScanIntervalHigh";
    public static final String SPREF_SCAN_INTERVAL_MEDIUM = "SPScanIntervalMedium";
    public static final String SPREF_SCAN_INTERVAL_LOW    = "SPScanIntervalLow";

    /* Android connection service status, the service maybe running but not
     * connected.
     * type -- Boolean
     *
     * __Values__
     * true  -- Service is running
     * false -- Service not running
     */
    public static final String SPREF_CONNECTION_SERVICE_RUNNING = "SPConnectionServiceRunning";

    /*
     * Android location service status.
     * type -- Boolean
     *
     * __Values__
     * true  -- Service is running.
     * false -- Service is not running.
     */
    public static final String SPREF_LOCATION_SERVICE_RUNNING = "SPLocationServiceRunning";

    /*
     * Android s2pa service status.
     * type -- Boolean
     *
     * __Values__
     * true  -- Service is running.
     * false -- Service is not running.
     */
    public static final String SPREF_S2PA_SERVICE_RUNNING = "SPS2PAServiceRunning";

    /* Connection status with the gateway.
     * type -- Boolean
     *
     * __Values__
     * true  -- Connected
     * false -- Not connected
     */
    public static final String SPREF_IS_CONNECTED = "SPIsConnected";

    /* Movement status of the vehicle-person.
     * type -- Boolean
     *
     * __Values__
     * true  - Vehicle-Person is moving
     * false - Vehicle-Person not moving
     * */
    public static final String SPREF_IS_MOVING = "SPIsMoving";

    /*
     * Keep tracking the factor when to send the next signal when the vehicle
     * is not moving.
     * type -- Integer
     */
    public static final String SPREF_NOT_MOVING_FACTOR = "SPNotMovingFactor";

    /*
     * The accuracy of the point acquired by the GPS. It is going to be used as
     * a max value to obtain a good position value.
     */
    public static final String SPREF_ACCURACY_UPPER_BOUND = "SPAccuracyUpperBound";

    /*
     * The number of unread messages, an alert will show on top of the messages
     * icon.
     */
    public static final String SPREF_UNREAD_MESSAGES = "SPUnreadMessages";

    /*
     * If the M-Hub should connect to the mobile objects after they are found
     * type -- Boolean
     */
    public static final String SPREF_AUTOCONNECT_MO = "SPAutoConnectMO";

    /* Threshold of the range accepted for M-Objects
     * type -- Integer
     * */
    public static final String SPREF_SINGAL_RANGE_MO = "SPSignalRangeMO";

    /**
     * }} ======================================================================
     */

    /**
     * Default values
     * {{ ======================================================================
     */

	/* The range used by the HIGH, MEDIUM and LOW values. this is by default
	 * the scan intervals.
	 *
	 * HIGH   :: 20 seconds
	 * MEDIUM :: 30 seconds
	 * LOW    :: 40 seconds
	 */
    public static final int DEFAULT_SCAN_INTERVAL_HIGH   = 1000 * 20;
    public static final int DEFAULT_SCAN_INTERVAL_MEDIUM = 1000 * 30;
    public static final int DEFAULT_SCAN_INTERVAL_LOW    = 1000 * 40;

    /* The range used by the HIGH, MEDIUM and LOW values. this is by default
     * the battery life.
     *
     * HIGH   :: between 100% and 70%
     * MEDIUM :: between 70% and 40%
     * LOW    :: between 40% and 0%
     */
    public static final int DEFAULT_HIGH_VALUE   = 70;
    public static final int DEFAULT_MEDIUM_VALUE = 40;
    public static final int DEFAULT_LOW_VALUE    = 0;

    /* Default interval values to send messages (milliseconds), it is used by
     * the connection service.
     *
     * HIGH   ::  2 minutes
     * MEDIUM ::  6 minutes
     * LOW    :: 30 minutes
     */
    public static final int DEFAULT_SEND_SIGNALS_INTERVAL_HIGH   = 1000 * 30;
    public static final int DEFAULT_SEND_SIGNALS_INTERVAL_MEDIUM = 1000 * 60;
    public static final int DEFAULT_SEND_SIGNALS_INTERVAL_LOW    = 1000 * 60 * 2;

    /*
     * Default interval values to get a new location (milliseconds).
     *
     * HIGH   ::  30 seconds
     * MEDIUM ::   2 minutes
     * LOW    ::  30 minutes
     */
    public static final int DEFAULT_LOCATION_INTERVAL_HIGH   = 1000 * 10;
    public static final int DEFAULT_LOCATION_INTERVAL_MEDIUM = 1000 * 30;
    public static final int DEFAULT_LOCATION_INTERVAL_LOW    = 1000 * 60;

    /*
     * Default value of delay to start the first scan
     *
     * Default: 1 second
     */
    public static final Integer DEFAULT_DELAY_SCAN_PERIOD = 1000;

    /*
     * Default value of scan period
     *
     * Default: 2 seconds
     */
    public static final Integer DEFAULT_SCAN_PERIOD = 1000 * 2;

    /*
     * Default value to get a new location when the distance is greater than
     * this value.
     *
     * Default: 0 meters
     */
    public static final Integer DEFAULT_LOCATION_MIN_DISTANCE = 0;

    /* Default interval factor when the vehicle is not moving, the first value
     * will be the default, the second will be the first value multiplied by
     * the factor in miliseconds.
     *
     * Default: 30 minutes
     */
    public static final Integer DEFAULT_NOT_MOVING_FACTOR = 1000 * 60 * 30;

    /*
     * Default upper bound value for the accuracy. When GPS acquires a new
     * location, this point has an accuracy value showing a circular area that
     * the point will be in.
     *
     * Default: 80 meters
     */
    public static final Integer DEFAULT_ACCURACY_UPPER_BOUND = 80;

    /*
     * Default value for the auto connect to mobile objects
     *
     * Default: true
     */
    public static final Boolean DEFAULT_AUTOCONNECT_MO = true;

    /*
     * Default value for the signal allowed to connect to mobile objects
     *
     * Default: 0
     */
    public static final Integer DEFAULT_SIGNAL_RANGE_MO = -100;

    /*
     * Default value for the ip address, the first address that the device
     * will connect to.
     *
     * Default: onDevelopment --// not set //--
     */
    public static final String DEFAULT_SDDL_IP_ADDRESS = "192.168.10.231";

    /*
     * Default value for the port used by the SDDL.
     *
     * Default: 5500
     */
    public static final Integer DEFAULT_SDDL_PORT = 5500;

    /*
     * Default value for the total of unread messages.
     *
     * Default: 0
     */
    public static final Integer DEFAULT_UNREAD_MESSAGES = 0;

    /**
     * }} ======================================================================
     */
}
