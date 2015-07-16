package br.com.alexpfx.irctest.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.Toast;
import br.com.alexpfx.irctest.app.irc.WifiReceivedEvent;
import com.squareup.otto.Bus;

import java.util.List;

/**
 * Created by alexandre on 27/06/15.
 */
public class WifiScanResultBroadcastReceiver extends BroadcastReceiver {

    private String tag = WifiNetworkInfoReceiveListener.class.getSimpleName();
    private Bus bus = BusProvider.INSTANCE.get();

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
        final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        final List<ScanResult> scanResults = wifiManager.getScanResults();
        WifiList list = new WifiList();
        for (ScanResult scanResult : scanResults) {
            WifiInfo wifiInfo = WifiInfo.newInstance(scanResult.BSSID, scanResult.SSID, scanResult.frequency);
            list.add(wifiInfo);
        }
        bus.post(new WifiReceivedEvent(list));
    }

    public interface WifiNetworkInfoReceiveListener {
        void receive(String bssid, String ssid, int rssid);
    }

}
