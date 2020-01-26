package com.shivasnape.mydeviceutil;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class DeviceUtil {

    private final String BATTERY_HEALTH_COLD = "cold";
    private final String BATTERY_HEALTH_DEAD = "dead";
    private final String BATTERY_HEALTH_GOOD = "good";
    private final String BATTERY_HEALTH_OVERHEAT = "Over Heat";
    private final String BATTERY_HEALTH_OVER_VOLTAGE = "Over Voltage";
    private final String BATTERY_HEALTH_UNKNOWN = "Unknown";
    private final String BATTERY_HEALTH_UNSPECIFIED_FAILURE = "Unspecified failure";


    private final String BATTERY_PLUGGED_AC = "Charging via AC";
    private final String BATTERY_PLUGGED_USB = "Charging via USB";
    private final String BATTERY_PLUGGED_WIRELESS = "Wireless";
    private final String BATTERY_PLUGGED_UNKNOWN = "Unknown Source";

    private final String RINGER_MODE_NORMAL = "Normal";
    private final String RINGER_MODE_SILENT = "Silent";
    private final String RINGER_MODE_VIBRATE = "Vibrate";


    private final String PHONE_TYPE_GSM = "GSM";
    private final String PHONE_TYPE_CDMA = "CDMA";
    private final String PHONE_TYPE_NONE = "Unknown";


    private final String NETWORK_TYPE_2G = "2G";
    private final String NETWORK_TYPE_3G = "3G";
    private final String NETWORK_TYPE_4G = "4G";
    private final String NETWORK_TYPE_WIFI_WIFIMAX = "WiFi";


    private final String NOT_FOUND_VAL = "unknown";

    private Context _context;
    private BatteryInfo mBatteryInfo;
    private MemoryInfo mMemoryInfo;
    private TelephonyInfo mTelephonyInfo;

    public DeviceUtil(Context context) {
        this._context = context;
        mBatteryInfo = new BatteryInfo(_context);
        mMemoryInfo = new MemoryInfo(_context);
        mTelephonyInfo = new TelephonyInfo(_context);
    }

    public BatteryInfo getBatteryInfo() {
        return mBatteryInfo;

    }

    public MemoryInfo getMemoryInfo() {
        return mMemoryInfo;
    }

    public TelephonyInfo getTelephonyInfo() {
        return mTelephonyInfo;
    }


    /*get device unique serial*/
    @SuppressLint("MissingPermission")
    String getSerial() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Build.getSerial();
        } else {
            return Build.SERIAL;
        }
    }

    /*get device brand*/
    String getBrand() {
        return Build.BRAND;
    }

    /*get build version code name*/
    String getBuildVersionCodeName() {
        return Build.VERSION.CODENAME;
    }

    /*get fingerprint details*/
    String getFingerPrint() {
        return Build.FINGERPRINT;
    }

    /*get device product*/
    String getProduct() {
        return Build.PRODUCT;
    }

    /*get diceice build host*/
    String getHost() {
        return Build.HOST;
    }

    /*get device board*/
    String getBoard() {
        return Build.BOARD;
    }

    /*get device*/
    String getDevice() {
        return Build.DEVICE;
    }

    /*get device hardware*/
    String gethardware() {
        return Build.HARDWARE;
    }

    /*get device build id*/
    String getID() {
        return Build.ID;
    }

    /*get deive manufacturer name*/
    String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /*get deice model*/
    String getModel() {
        return Build.MODEL;
    }

    /*get OS version*/
    String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    /*get sdk version*/
    int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /*get display version*/
    String getDisplay() {
        return Build.DISPLAY;
    }

    /*get radio version*/
    String getRadioVersion() {
        return Build.getRadioVersion();
    }

    /*get bootloader*/
    String getBootloader() {
        return Build.BOOTLOADER;
    }

    /*get CPU_ABI*/
    String getCPU_ABI() {
        return Build.CPU_ABI;
    }

    /*get CPU_ABI_2*/
    String getCPU_ABI_2() {
        return Build.CPU_ABI2;
    }

    /*get build tag*/
    String getTags() {
        return Build.TAGS;
    }

    /*get build time*/
    Long getBuildTime() {
        return Build.TIME;
    }

    /*get build type*/
    String getBuildType() {
        return Build.TYPE;
    }

    /*get unknown details*/
    String getUnknown() {
        return Build.UNKNOWN;
    }

    /*get user*/
    String getUser() {
        return Build.USER;
    }

    /*get screen density*/
    String getScreenDensity() {
        int density = _context.getResources().getDisplayMetrics().densityDpi;
        String scrType = "";
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                scrType = "ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                scrType = "mdpi";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                scrType = "hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                scrType = "xhdpi";
                break;
            default:
                scrType = "other";
                break;
        }
        return scrType;
    }

    /*get screen height*/
    int getScreenHeight() {
        int height = 0;
        WindowManager wm = (WindowManager) _context.getSystemService(Context.WINDOW_SERVICE);
        Display display = null;
        if (wm != null) {
            display = wm.getDefaultDisplay();
        }
        Point size = new Point();
        if (display != null) {
            display.getSize(size);
        }
        height = size.y;
        return height;
    }

    /*get screen width*/
    int getScreenWidth() {
        int width = 0;
        WindowManager wm = (WindowManager) _context.getSystemService(Context.WINDOW_SERVICE);
        Display display = null;
        if (wm != null) {
            display = wm.getDefaultDisplay();
        }
        Point size = new Point();
        if (display != null) {
            display.getSize(size);
        }
        width = size.x;
        return width;
    }

    /* get bluetooth MAC */
    @SuppressLint("HardwareIds")
    @SuppressWarnings("MissingPermission")
    public final String getBluetoothMAC() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.Secure.getString(_context.getContentResolver(),
                    "bluetooth_address");
        } else {
            BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
            return bta != null ? bta.getAddress() : "00";
        }
    }

    /*get WIFI MAC*/
    @SuppressLint("HardwareIds")
    @SuppressWarnings("MissingPermission")
    public final String getWifiMacAddress() {
        WifiManager manager = (WifiManager) _context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = null;
        if (manager != null) {
            info = manager.getConnectionInfo();
        }
        @SuppressLint("HardwareIds") String address = null;
        if (info != null) {
            address = info.getMacAddress();
        }
        return address;
    }

    /*get deive default ring mode*/
    public String getDeviceRingerMode() {
        AudioManager audioManager = (AudioManager) _context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            switch (audioManager.getRingerMode()) {
                case AudioManager.RINGER_MODE_SILENT:
                    return RINGER_MODE_SILENT;
                case AudioManager.RINGER_MODE_VIBRATE:
                    return RINGER_MODE_VIBRATE;
                default:
                    return RINGER_MODE_NORMAL;
            }
        } else {
            return null;
        }
    }

    /*check whether the device is rooted*/
    public final boolean isDeviceRooted() {
        String[] paths = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (String path : paths) {
            if (new File(path).exists()) return true;
        }
        return false;
    }

    /*get email accounts*/
    @SuppressWarnings("MissingPermission")
    public final List<String> getEmailAccounts() {
        Set<String> emails = new HashSet<String>();
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(_context).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                emails.add(account.name);
            }
        }
        return new ArrayList<String>(new LinkedHashSet<String>(emails));
    }

    /*get android unique ID*/
    public final String getAndroidId() {
        @SuppressLint("HardwareIds") String androidId = Settings.Secure.getString(_context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return androidId;
    }

    /*get install source*/
    public String getInstallSource() {
        PackageManager pm = _context.getPackageManager();
        return pm.getInstallerPackageName(_context.getPackageName());
    }

    /*get user agent*/
    public final String getUserAgent() {
        final String systemUa = System.getProperty("http.agent");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return WebSettings.getDefaultUserAgent(_context) + "__" + systemUa;
        }
        return new WebView(_context).getSettings().getUserAgentString() + "__" + systemUa;
    }

    /*get GSF ID*/
    public final String getGSFId() {
        Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
        String ID_KEY = "android_id";
        String[] params = {ID_KEY};
        Cursor c = _context.getContentResolver().query(URI, null, null, params, null);

        if (c != null && (!c.moveToFirst() || c.getColumnCount() < 2)) {
            c.close();
            return NOT_FOUND_VAL;
        }
        try {
            String gsfId = null;
            if (c != null) {
                gsfId = Long.toHexString(Long.parseLong(c.getString(1)));
            }
            if (c != null) {
                c.close();
            }
            return gsfId;
        } catch (NumberFormatException e) {
            c.close();
            return NOT_FOUND_VAL;
        }
    }

    /*check for NFC*/
    public final boolean isNfcPresent() {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(_context);
        return nfcAdapter != null;
    }

    /*is nfc enabled or disabled*/
    public final boolean isNfcEnabled() {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(_context);
        return nfcAdapter != null && nfcAdapter.isEnabled();
    }

    /*is wifi enabled*/
    public final boolean isWifiEnabled() {
        WifiManager wifiManager = (WifiManager) _context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            return wifiManager.isWifiEnabled();
        } else {
            return false;
        }
    }


    /*for battery info*/
    class BatteryInfo {

        private Context __context;

        public BatteryInfo(Context context) {
            this.__context = context;
        }

        private Intent getBatteryStatusIntent() {
            IntentFilter batFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            return _context.registerReceiver(null, batFilter);
        }

        public int getBatteryPercent() {
            Intent intent = getBatteryStatusIntent();
            int rawlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int level = -1;
            if (rawlevel >= 0 && scale > 0) {
                level = (rawlevel * 100) / scale;
            }
            return level;
        }

        public boolean isPhoneCharging() {
            Intent intent = getBatteryStatusIntent();
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
        }

        public String getBatteryHealth() {
            String health = BATTERY_HEALTH_UNKNOWN;
            Intent intent = getBatteryStatusIntent();
            int status = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            switch (status) {
                case BatteryManager.BATTERY_HEALTH_COLD:
                    health = BATTERY_HEALTH_COLD;
                    break;

                case BatteryManager.BATTERY_HEALTH_DEAD:
                    health = BATTERY_HEALTH_DEAD;
                    break;

                case BatteryManager.BATTERY_HEALTH_GOOD:
                    health = BATTERY_HEALTH_GOOD;
                    break;

                case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                    health = BATTERY_HEALTH_OVERHEAT;
                    break;

                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                    health = BATTERY_HEALTH_OVER_VOLTAGE;
                    break;

                case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                    health = BATTERY_HEALTH_UNKNOWN;
                    break;

                case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                    health = BATTERY_HEALTH_UNSPECIFIED_FAILURE;
                    break;
            }
            return health;
        }

        public final String getBatteryTechnology() {
            Intent intent = getBatteryStatusIntent();
            return intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
        }

        public final float getBatteryTemperature() {
            Intent intent = getBatteryStatusIntent();
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            return (float) (temperature / 10.0);
        }

        public final int getBatteryVoltage() {
            Intent intent = getBatteryStatusIntent();
            return intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
        }

        public final String getChargingSource() {
            Intent intent = getBatteryStatusIntent();
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            switch (plugged) {
                case BatteryManager.BATTERY_PLUGGED_AC:
                    return BATTERY_PLUGGED_AC;
                case BatteryManager.BATTERY_PLUGGED_USB:
                    return BATTERY_PLUGGED_USB;
                case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                    return BATTERY_PLUGGED_WIRELESS;
                default:
                    return BATTERY_PLUGGED_UNKNOWN;
            }
        }

        public final boolean isBatteryPresent() {
            Intent intent = getBatteryStatusIntent();
            return intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
        }
    }

    /*MEMORY INFO*/
    class MemoryInfo {

        private Context ___context;

        public MemoryInfo(Context context) {
            this.___context = context;
        }

        boolean hasExternalSDCard() {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        }


        final String getTotalRAM() {
            long totalMemory = 0;
            ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
            ActivityManager activityManager = (ActivityManager) ___context.getSystemService(Activity.ACTIVITY_SERVICE);
            if (activityManager != null) {
                activityManager.getMemoryInfo(mi);
            }
            return humanReadableByteCountSI(mi.totalMem);
        }

        final String getAvailableInternalMemorySize() {
            File path = Environment.getDataDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize, availableBlocks;
            blockSize = stat.getBlockSizeLong();
            availableBlocks = stat.getAvailableBlocksLong();
            return humanReadableByteCountSI(availableBlocks * blockSize);
        }

        final String getTotalInternalMemorySize() {
            File path = Environment.getDataDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize;
            long totalBlocks;
            blockSize = stat.getBlockSizeLong();
            totalBlocks = stat.getBlockCountLong();
            return humanReadableByteCountSI(totalBlocks * blockSize);
        }


        final String getAvailableExternalMemorySize() {
            if (hasExternalSDCard()) {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize;
                long availableBlocks;
                blockSize = stat.getBlockSizeLong();
                availableBlocks = stat.getAvailableBlocksLong();
                return humanReadableByteCountSI(availableBlocks * blockSize);
            }
            return null;
        }


        final String getTotalExternalMemorySize() {
            if (hasExternalSDCard()) {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize;
                long totalBlocks;
                blockSize = stat.getBlockSizeLong();
                totalBlocks = stat.getBlockCountLong();
                return humanReadableByteCountSI(totalBlocks * blockSize);
            }
            return null;
        }
    }

    /*Telephony Info*/
    class TelephonyInfo {

        private Context context;

        public TelephonyInfo(Context context) {
            this.context = context;
        }

        @SuppressLint({"MissingPermission", "HardwareIds"})
        final String getIMEI() {
            TelephonyManager telephonyMgr = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
            if (telephonyMgr != null) {
                return telephonyMgr.getDeviceId();
            } else {
                return null;
            }
        }

        @SuppressLint({"MissingPermission", "HardwareIds"})
        final String getIMSI() {
            TelephonyManager telephonyMgr = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
            if (telephonyMgr != null) {
                return telephonyMgr.getSubscriberId();
            } else {
                return null;
            }
        }

        final String getPhoneType() {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                switch (tm.getPhoneType()) {
                    case TelephonyManager.PHONE_TYPE_GSM:
                        return PHONE_TYPE_GSM;
                    case TelephonyManager.PHONE_TYPE_CDMA:
                        return PHONE_TYPE_CDMA;
                    case TelephonyManager.PHONE_TYPE_NONE:
                    default:
                        return PHONE_TYPE_NONE;
                }
            } else {
                return null;
            }
        }

        @SuppressLint("HardwareIds")
        @SuppressWarnings("MissingPermission")
        String getPhoneNumber() {
            String serviceName = Context.TELEPHONY_SERVICE;
            TelephonyManager m_telephonyManager = (TelephonyManager) context.getSystemService(serviceName);
            if (m_telephonyManager != null) {
                return m_telephonyManager.getLine1Number();
            } else {
                return "Not Found";
            }
        }


        String getOperator() {
            String operatorName = null;
            TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
            if (telephonyManager != null) {
                operatorName = telephonyManager.getNetworkOperatorName();
            }
            if (operatorName == null)
                if (telephonyManager != null) {
                    operatorName = telephonyManager.getSimOperatorName();
                }
            return operatorName;
        }

        @SuppressLint({"MissingPermission", "HardwareIds"})
        final String getSIMSerial() {
            TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
            if (telephonyManager != null) {
                return telephonyManager.getSimSerialNumber();
            } else {
                return null;
            }
        }

        final boolean isSimNetworkLocked() {
            TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
            if (telephonyManager != null) {
                return telephonyManager.getSimState() == TelephonyManager.SIM_STATE_NETWORK_LOCKED;
            } else {
                return false;
            }
        }

        String getNetworkClass() {
            TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int networkType = 0;
            if (mTelephonyManager != null) {
                networkType = mTelephonyManager.getNetworkType();
            }
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return NETWORK_TYPE_2G;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return NETWORK_TYPE_3G;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return NETWORK_TYPE_4G;
                default:
                    return NOT_FOUND_VAL;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private static String humanReadableByteCountSI(long bytes) {
        String s = bytes < 0 ? "-" : "";
        long b = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        return b < 1000L ? bytes + " B"
                : b < 999_950L ? String.format("%s%.1f kB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f MB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f GB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f TB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f PB", s, b / 1e3)
                : String.format("%s%.1f EB", s, b / 1e6);
    }
}
