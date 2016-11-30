package com.liuxd.firstblood.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.liuxd.firstblood.MyApp;

/**
 * Created by Liuxd on 2016/10/27 16:08.
 * 手机设备工具类
 */

public class DeviceUtil {
    /**
     * 获取IMEI号
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * @return 设备名称及sdk版本号
     */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        int sdk_int = Build.VERSION.SDK_INT;
        if (model.startsWith(manufacturer)) {
            return model + "_" + sdk_int;
        } else {
            return manufacturer + "_" + model + "_" + sdk_int;
        }
    }

    /**
     * @return 网络是否可用
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) MyApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isAvailable();
    }
}
