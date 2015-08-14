package br.com.alexpfx.irctest.app.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import br.com.alexpfx.android.lib.base.provider.BusProvider;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiInfo;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiInfoBag;
import br.com.alexpfx.irctest.app.ottobus.events.WifiReceived;
import com.squareup.otto.Bus;

import java.util.List;

/**
 * Created by alexandre on 27/06/15.
 */
public class WifiScanResultReceiver extends BroadcastReceiver {

    private Bus bus = BusProvider.getInstance();

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
        final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        final List<ScanResult> scanResults = wifiManager.getScanResults();
        WifiInfoBag list = new WifiInfoBag();
        for (ScanResult scanResult : scanResults) {
            WifiInfo wifiInfo = WifiInfo.newInstance(scanResult.BSSID, scanResult.SSID, scanResult.level);
            list.add(wifiInfo);
        }
        bus.post(new WifiReceived(list));
    }

}
