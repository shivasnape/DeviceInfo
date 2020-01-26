package com.shivasnape.mydeviceutil;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private DeviceUtil deviceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkForPermissionandContinue();
        } else {
            showDeviceInfoinLog();
        }
    }

    private void checkForPermissionandContinue() {

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.GET_ACCOUNTS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

                showDeviceInfoinLog();

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                token.continuePermissionRequest();
            }

        }).withErrorListener(error -> {

            Timber.e(error.toString());
            Toast.makeText(getApplicationContext(), "Please grant required permissins", Toast.LENGTH_SHORT).show();

        }).check();
    }

    @SuppressLint("BinaryOperationInTimber")
    private void showDeviceInfoinLog() {

        deviceUtil = new DeviceUtil(getApplicationContext());

        String string = "DEVICE INFO \n\n" + "Serial : " + deviceUtil.getSerial() + "\n" +
                "build Brand : " + deviceUtil.getBrand() + "\n" +
                "Build Version Code Name " + deviceUtil.getBuildVersionCodeName() + "\n" +
                "FingerPrint :" + deviceUtil.getFingerPrint() + "\n" +
                "Product : " + deviceUtil.getProduct() + "\n" +
                "Build Host : " + deviceUtil.getHost() + "\n" +
                "Board : " + deviceUtil.getBoard() + "\n" +
                "Device : " + deviceUtil.getDevice() + "\n" +
                "Hardware : " + deviceUtil.gethardware() + "\n" +
                "ID : " + deviceUtil.getID() + "\n" +
                "Manufacturer : " + deviceUtil.getManufacturer() + "\n" +
                "Model : " + deviceUtil.getModel() + "\n" +
                "OS Version : " + deviceUtil.getOSVersion() + "\n" +
                "SDK Version : " + deviceUtil.getSDKVersion() + "\n" +
                "Display Version : " + deviceUtil.getDisplay() + "\n" +
                "Radio Version : " + deviceUtil.getRadioVersion() + "\n" +
                "Bootloader : " + deviceUtil.getBootloader() + "\n" +
                "CPU-ABI : " + deviceUtil.getCPU_ABI() + "\n" +
                "CPU-ABI2 : " + deviceUtil.getCPU_ABI_2() + "\n" +
                "Tags : " + deviceUtil.getTags() + "\n" +
                "Build Time : " + convertTime(deviceUtil.getBuildTime()) + "\n" +
                "Build Type : " + deviceUtil.getBuildType() + "\n" +
                "Unknown : " + deviceUtil.getUnknown() + "\n" +
                "User : " + deviceUtil.getUser() + "\n" +
                "Android ID : " + deviceUtil.getAndroidId() + "\n" +
                "Bluetooth MAC : " + deviceUtil.getBluetoothMAC() + "\n" +
                "WIFI MAC : " + deviceUtil.getWifiMacAddress() + "\n" +
                "Screen Density : " + deviceUtil.getScreenDensity() + "\n" +
                "Screen Height : " + deviceUtil.getScreenHeight() + "\n" +
                "Screen Width : " + deviceUtil.getScreenWidth() + "\n" +
                "Ringer Mode : " + deviceUtil.getDeviceRingerMode() + "\n" +
                "GFS ID : " + deviceUtil.getGSFId() + "\n" +
                "Install Source : " + deviceUtil.getInstallSource() + "\n" +
                "Email Account : " + deviceUtil.getEmailAccounts() + "\n" +
                "User Agent : " + deviceUtil.getUserAgent() + "\n\n\n" +
                "Android ID : " + deviceUtil.getAndroidId() + "\n" +
                "Bluetooth MAC : " + deviceUtil.getBluetoothMAC() + "\n" +
                "WIFI MAC : " + deviceUtil.getWifiMacAddress() + "\n" +
                "Screen Density : " + deviceUtil.getScreenDensity() + "\n" +
                "Screen Height : " + deviceUtil.getScreenHeight() + "\n" +
                "Screen Width : " + deviceUtil.getScreenWidth() + "\n" +
                "Ringer Mode : " + deviceUtil.getDeviceRingerMode() + "\n" +
                "GFS ID : " + deviceUtil.getGSFId() + "\n" +
                "Install Source : " + deviceUtil.getInstallSource() + "\n" +
                "Email Account : " + deviceUtil.getEmailAccounts() + "\n\n\n" +

                "Battery Info \n\n" +
                "Battery % : " + deviceUtil.getBatteryInfo().getBatteryPercent() + "\n" +
                "Battery Health : " + deviceUtil.getBatteryInfo().getBatteryHealth() + "\n" +
                "Battery Technology : " + deviceUtil.getBatteryInfo().getBatteryTechnology() + "\n" +
                "Battery Charging Source : " + deviceUtil.getBatteryInfo().getChargingSource() + "\n" +
                "Battery Temperature : " + deviceUtil.getBatteryInfo().getBatteryTemperature() + "\n" +
                "Battery Voltage : " + deviceUtil.getBatteryInfo().getBatteryVoltage() + "\n\n\n" +

                "Memory Info \n\n" +
                "Has External SD : " + deviceUtil.getMemoryInfo().hasExternalSDCard() + "\n" +
                "Total RAM : " + deviceUtil.getMemoryInfo().getTotalRAM() + "\n" +
                "Total Internal : " + deviceUtil.getMemoryInfo().getTotalInternalMemorySize() + "\n" +
                "Available Internal : " + deviceUtil.getMemoryInfo().getAvailableInternalMemorySize() + "\n" +
                "Total External : " + deviceUtil.getMemoryInfo().getTotalExternalMemorySize() + "\n" +
                "Available External : " + deviceUtil.getMemoryInfo().getAvailableExternalMemorySize() + "\n\n\n" +

                "Network Info \n\n" +
                "IMEI : " + deviceUtil.getTelephonyInfo().getIMEI() + "\n" +
                "IMSI : " + deviceUtil.getTelephonyInfo().getIMSI() + "\n" +
                "Network Type : " + deviceUtil.getTelephonyInfo().getNetworkClass() + "\n" +
                "Operator : " + deviceUtil.getTelephonyInfo().getOperator() + "\n" +
                "Phone Number : " + deviceUtil.getTelephonyInfo().getPhoneNumber() + "\n" +
                "Phone Type : " + deviceUtil.getTelephonyInfo().getPhoneType() + "\n" +
                "Sim Serial : " + deviceUtil.getTelephonyInfo().getSIMSerial() + "\n" +
                "Is Sim Network Locked : " + deviceUtil.getTelephonyInfo().isSimNetworkLocked() + "\n\n";

        textView.setText(string);

        Timber.e("Serial : " + deviceUtil.getSerial());
        Timber.e("build Brand : " + deviceUtil.getBrand());
        Timber.e("Build Version Code Name " + deviceUtil.getBuildVersionCodeName());
        Timber.e("FingerPrint :" + deviceUtil.getFingerPrint());
        Timber.e("Product : " + deviceUtil.getProduct());
        Timber.e("Build Host : " + deviceUtil.getHost());
        Timber.e("Board : " + deviceUtil.getBoard());
        Timber.e("Device : " + deviceUtil.getDevice());
        Timber.e("Hardware : " + deviceUtil.gethardware());
        Timber.e("ID : " + deviceUtil.getID());
        Timber.e("Manufacturer : " + deviceUtil.getManufacturer());
        Timber.e("Model : " + deviceUtil.getModel());
        Timber.e("OS Version : " + deviceUtil.getOSVersion());
        Timber.e("SDK Version : " + deviceUtil.getSDKVersion() + "");
        Timber.e("Display Version : " + deviceUtil.getDisplay());
        Timber.e("Radio Version : " + deviceUtil.getRadioVersion());
        Timber.e("Bootloader : " + deviceUtil.getBootloader());
        Timber.e("CPU-ABI : " + deviceUtil.getCPU_ABI());
        Timber.e("CPU-ABI2 : " + deviceUtil.getCPU_ABI_2());
        Timber.e("Tags : " + deviceUtil.getTags());
        Timber.e("Build Time : " + convertTime(deviceUtil.getBuildTime()));
        Timber.e("Build Type : " + deviceUtil.getBuildType());
        Timber.e("Unknown : " + deviceUtil.getUnknown());
        Timber.e("User : " + deviceUtil.getUser());

        Timber.e("******************************************************************************************");
        Timber.e("******************************************************************************************");

        Timber.e("Android ID : " + deviceUtil.getAndroidId());
        Timber.e("Bluetooth MAC : " + deviceUtil.getBluetoothMAC());
        Timber.e("WIFI MAC : " + deviceUtil.getWifiMacAddress());
        Timber.e("Screen Density : " + deviceUtil.getScreenDensity());
        Timber.e("Screen Height : " + deviceUtil.getScreenHeight());
        Timber.e("Screen Width : " + deviceUtil.getScreenWidth());
        Timber.e("Ringer Mode : " + deviceUtil.getDeviceRingerMode());
        Timber.e("GFS ID : " + deviceUtil.getGSFId());
        Timber.e("Install Source : " + deviceUtil.getInstallSource());
        Timber.e("Email Account : " + deviceUtil.getEmailAccounts());
        Timber.e("User Agent : " + deviceUtil.getUserAgent());


        Timber.e("******************************************************************************************");
        Timber.e("******************************************************************************************");

        Timber.e("Battery % " + deviceUtil.getBatteryInfo().getBatteryPercent());
        Timber.e("Battery Health " + deviceUtil.getBatteryInfo().getBatteryHealth());
        Timber.e("Battery Technology " + deviceUtil.getBatteryInfo().getBatteryTechnology());
        Timber.e("Battery Charging Source " + deviceUtil.getBatteryInfo().getChargingSource());
        Timber.e("Battery Temperature " + deviceUtil.getBatteryInfo().getBatteryTemperature());
        Timber.e("Battery Voltage " + deviceUtil.getBatteryInfo().getBatteryVoltage());

        Timber.e("******************************************************************************************");
        Timber.e("******************************************************************************************");

        Timber.e("Has External SD " + deviceUtil.getMemoryInfo().hasExternalSDCard());
        Timber.e("Total RAM " + deviceUtil.getMemoryInfo().getTotalRAM());
        Timber.e("Total Internal " + deviceUtil.getMemoryInfo().getTotalInternalMemorySize());
        Timber.e("Available Internal " + deviceUtil.getMemoryInfo().getAvailableInternalMemorySize());
        Timber.e("Total External " + deviceUtil.getMemoryInfo().getTotalExternalMemorySize());
        Timber.e("Available External " + deviceUtil.getMemoryInfo().getAvailableExternalMemorySize());

        Timber.e("******************************************************************************************");
        Timber.e("******************************************************************************************");


        Timber.e("IMEI " + deviceUtil.getTelephonyInfo().getIMEI());
        Timber.e("IMSI " + deviceUtil.getTelephonyInfo().getIMSI());
        Timber.e("Network Type " + deviceUtil.getTelephonyInfo().getNetworkClass());
        Timber.e("Operator " + deviceUtil.getTelephonyInfo().getOperator());
        Timber.e("Phone Number " + deviceUtil.getTelephonyInfo().getPhoneNumber());
        Timber.e("Phone Type " + deviceUtil.getTelephonyInfo().getPhoneType());
        Timber.e("Sim Serial " + deviceUtil.getTelephonyInfo().getSIMSerial());
        Timber.e("Is Sim Network Locked " + deviceUtil.getTelephonyInfo().isSimNetworkLocked());

    }

    public String convertTime(long time) {
        Date date = new Date(time);
        @SuppressLint("SimpleDateFormat") Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }
}
