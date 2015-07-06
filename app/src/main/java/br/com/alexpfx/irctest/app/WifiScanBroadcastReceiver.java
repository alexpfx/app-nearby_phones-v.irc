package br.com.alexpfx.irctest.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by alexandre on 27/06/15.
 */
public class WifiScanBroadcastReceiver extends BroadcastReceiver {

    private String tag = WifiNetworkInfoReceiveListener.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
        final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        final List<ScanResult> scanResults = wifiManager.getScanResults();
        for (ScanResult scanResult : scanResults) {
            Log.d(tag, scanResult.SSID);
        }
    }

    public interface WifiNetworkInfoReceiveListener {
        void receive(String bssid, String ssid, int rssid);
    }

}
