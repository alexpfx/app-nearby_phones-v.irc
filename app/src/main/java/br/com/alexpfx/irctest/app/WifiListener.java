package br.com.alexpfx.irctest.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 27/06/15.
 */
public class WifiListener extends BroadcastReceiver {
    public static final String TAG = WifiListener.class.getSimpleName();
    private WifiManager wifiManager;
    private final Handler handler = new Handler();
    private Context context;
    private List<WifiNetworkInfoReceiveListener> listeners;
    private static final int LOOP_INTERVAL = 10000;

    public WifiListener(WifiManager wifiManager, Context context) {
        this.wifiManager = wifiManager;
        this.context = context;
        this.listeners = new ArrayList<WifiNetworkInfoReceiveListener>();
    }

    public void registerReceiver() {
        context.registerReceiver(this, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    public void unregisterReceiver() {
        context.unregisterReceiver(this);
    }

    public void scan() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wifiManager.startScan();
                handler.postDelayed(this, LOOP_INTERVAL);
            }
        }, 1000);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final List<ScanResult> scanResults = wifiManager.getScanResults();
        for (ScanResult scanResult : scanResults) {
            for (WifiNetworkInfoReceiveListener l : listeners) {
                l.receive(scanResult.BSSID, scanResult.SSID, scanResult.level);
            }
        }
    }

    public void addListener(WifiNetworkInfoReceiveListener listener) {
        listeners.add(listener);
    }

    public void removeListener(WifiNetworkInfoReceiveListener listener) {
        listeners.remove(listener);
    }

    public static interface WifiNetworkInfoReceiveListener {
        void receive(String bssid, String ssid, int rssid);
    }

}
