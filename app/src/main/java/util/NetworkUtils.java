package util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE
        );

        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            return info != null && info.isConnected();
        }

        return false;
    }

}