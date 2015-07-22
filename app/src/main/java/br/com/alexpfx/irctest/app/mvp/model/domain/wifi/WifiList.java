package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexandre on 28/06/15.
 */
public class WifiList {
    /*nao precisa mais ser synchronized. */
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

    public Iterator<WifiInfo> iterator() {
        return wifiInfoList.iterator();
    }

    public List<SimpleWifiInfo> getSimpleInfoList() {
        List<SimpleWifiInfo> s = new ArrayList<SimpleWifiInfo>();
        synchronized (wifiInfoList) {
            for (WifiInfo wifiInfo : wifiInfoList) {
                SimpleWifiInfo si = new SimpleWifiInfo(wifiInfo.getBssid(), wifiInfo.getRssid());
                s.add(si);
            }
        }
        return s;
    }

    public List<List<SimpleWifiInfo>> getSplitedSimpleInfoList(int size) {
        return ListUtils.partition(getSimpleInfoList(), size);
    }

    public int getSize() {
        synchronized (wifiInfoList) {
            return wifiInfoList.size();
        }
    }

}
