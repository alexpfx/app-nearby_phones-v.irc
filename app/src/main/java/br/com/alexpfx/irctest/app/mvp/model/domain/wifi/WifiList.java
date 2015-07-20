package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexandre on 28/06/15.
 */
public class WifiList {
    private List<WifiInfo> wifiInfoList = Collections.synchronizedList(new ArrayList<WifiInfo>());

    public synchronized void add(WifiInfo info) {
        final WifiInfo byBssid = getByBssid(info.getBssid());
        if (byBssid != null) {
            byBssid.setSsid(info.getSsid());
            byBssid.setRssid(info.getRssid());
        } else {
            wifiInfoList.add(info);
        }
    }

    public void clear() {
        wifiInfoList.clear();
    }

    public WifiInfo getByBssid(String bssid) {
        synchronized (wifiInfoList) {
            for (WifiInfo info : wifiInfoList) {
                if (info.getBssid().equals(bssid)) {
                    return info;
                }
            }
        }
        return null;
    }

    public boolean contains(String bssid) {
        synchronized (wifiInfoList) {
            for (WifiInfo info : wifiInfoList) {
                if (info.getBssid().equals(bssid)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<WifiInfo> getWifiInfoList() {
        return wifiInfoList;
    }
}
