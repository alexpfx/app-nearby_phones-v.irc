package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

import java.util.List;

/**
 * Created by alexandre on 22/07/15.
 */
public class SimpleWifiInfoBag {
    private String appId;
    private List<SimpleWifiInfo> simpleWifiInfoList;

    public SimpleWifiInfoBag(String appId, List<SimpleWifiInfo> simpleWifiInfoList) {
        this.appId = appId;
        this.simpleWifiInfoList = simpleWifiInfoList;
    }

    public List<SimpleWifiInfo> getSimpleWifiInfoList() {
        return simpleWifiInfoList;
    }

    public String getAppId() {
        return appId;
    }
}
