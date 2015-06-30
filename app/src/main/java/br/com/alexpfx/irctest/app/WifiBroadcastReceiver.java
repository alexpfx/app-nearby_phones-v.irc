package br.com.alexpfx.irctest.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by alexandre on 27/06/15.
 */
public class WifiBroadcastReceiver extends BroadcastReceiver {
    private WifiManager wifiManager;
    private final WifiListener.WifiNetworkInfoReceiveListener listener;
    private static final String tag = WifiBroadcastReceiver.class.getName();

    public WifiBroadcastReceiver(WifiManager wifiManager, WifiListener.WifiNetworkInfoReceiveListener listener) {
        this.wifiManager = wifiManager;
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener == null) {
            return;
        }
        final List<ScanResult> scanResults = wifiManager.getScanResults();
        for (ScanResult scanResult : scanResults) {
            listener.receive(scanResult.BSSID, scanResult.SSID, scanResult.level);
        }
    }
}
