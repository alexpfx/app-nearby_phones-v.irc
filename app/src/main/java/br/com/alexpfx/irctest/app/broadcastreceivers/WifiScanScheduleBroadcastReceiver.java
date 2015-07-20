package br.com.alexpfx.irctest.app.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.widget.Toast;

/**
 * Created by alexandre on 06/07/15.
 */
public class WifiScanScheduleBroadcastReceiver extends BroadcastReceiver {

    public WifiScanScheduleBroadcastReceiver() {
        System.out.println();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        performWifiScan(context);
        Toast.makeText(context, "scanning...", Toast.LENGTH_SHORT).show();

        wl.release();

    }

    private void performWifiScan(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.startScan();
    }
}
