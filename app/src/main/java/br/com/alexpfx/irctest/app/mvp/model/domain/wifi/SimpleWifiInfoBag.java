package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

import java.util.List;

/**
 * Created by alexandre on 22/07/15.
 */
public class SimpleWifiInfoBag {
    /* app unique id */
    private String id;
    /* simpleWifiInfoList */
    private List<SimpleWifiInfo> list;

    public SimpleWifiInfoBag(String appUniqueId, List<SimpleWifiInfo> simpleWifiInfoList) {
        this.id = appUniqueId;
        this.list = simpleWifiInfoList;
    }

    public List<SimpleWifiInfo> getList() {
        return list;
    }

    public String getId() {
        return id;
    }
}
