package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexandre on 28/06/15.
 */
public class WifiList {
    private List<WifiInfo> wifiInfoList = new ArrayList<WifiInfo>();

    public void add(WifiInfo info) {
        wifiInfoList.add(info);
    }

    public Iterator<WifiInfo> iterator() {
        return wifiInfoList.iterator();
    }

    public List<SimpleWifiInfo> getSimpleInfoList() {
        List<SimpleWifiInfo> s = new ArrayList<SimpleWifiInfo>();
        for (WifiInfo wifiInfo : wifiInfoList) {
            SimpleWifiInfo si = new SimpleWifiInfo(wifiInfo.getBssid(), wifiInfo.getRssid());
            s.add(si);
        }
        return s;
    }

    public List<List<SimpleWifiInfo>> getSplitedSimpleInfoList(int size) {
        return ListUtils.partition(getSimpleInfoList(), size);
    }

}
