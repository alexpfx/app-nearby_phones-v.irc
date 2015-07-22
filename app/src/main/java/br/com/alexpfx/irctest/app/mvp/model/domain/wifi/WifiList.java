package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

    public Iterator<WifiInfo> iterator() {
        return wifiInfoList.iterator();
    }

    public List<WifiInfo.SimpleWifiInfo> getSimpleInfoList() {
        List<WifiInfo.SimpleWifiInfo> s = new ArrayList<WifiInfo.SimpleWifiInfo>();
        synchronized (wifiInfoList) {
            for (WifiInfo wifiInfo : wifiInfoList) {
                WifiInfo.SimpleWifiInfo si = new WifiInfo.SimpleWifiInfo();
                si.b = wifiInfo.getBssid();
                si.r = wifiInfo.getRssid();
                s.add(si);
            }
        }
        return s;
    }

    public List<List<WifiInfo.SimpleWifiInfo>> getSplitedSimpleInfoList(int size) {
        return ListUtils.partition(getSimpleInfoList(), size);
    }

    public int getSize() {
        synchronized (wifiInfoList) {
            return wifiInfoList.size();
        }
    }

}
