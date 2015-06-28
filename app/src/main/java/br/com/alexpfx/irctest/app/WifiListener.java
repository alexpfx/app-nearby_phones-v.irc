package br.com.alexpfx.irctest.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Handler;

import java.util.ArrayList;

/**
 * Created by alexandre on 27/06/15.
 */
public class WifiListener {
    private WifiManager wifiManager;
    private final Handler handler = new Handler();
    private Context context;
    private WifiBroadcastReceiver receiver;
    private WifiNetworkInfoReceiveListener listener;

    public WifiListener(WifiManager wifiManager, Context context, WifiNetworkInfoReceiveListener listener) {
        this.wifiManager = wifiManager;
        this.context = context;
        this.listener = listener;
    }


    private void enableWifi() {
        if (wifiManager.isWifiEnabled()) {
            return;
        }
//        wifiManager.setWifiEnabled(true);
    }

    public void registerReceiver() {
        if (receiver == null) {
            receiver = new WifiBroadcastReceiver(wifiManager, listener);
        }
        context.registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    public void unregisterReceiver() {
        context.unregisterReceiver(receiver);
    }

    public void scan() {
        enableWifi();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wifiManager.startScan();
            }
        }, 1000);
    }

    public static interface WifiNetworkInfoReceiveListener {
        void receive(String bssid, String ssid);
    }


}
