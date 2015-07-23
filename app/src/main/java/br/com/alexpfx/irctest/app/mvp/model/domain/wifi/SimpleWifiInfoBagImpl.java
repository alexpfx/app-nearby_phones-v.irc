package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

import java.util.List;

/**
 * Created by alexandre on 22/07/15.
 */
public class SimpleWifiInfoBagImpl implements SimpleWifiInfoBag {
    /* app unique id */
    private String id;
    /* simpleWifiInfoList */
    private List<SimpleWifiInfo> list;

    public SimpleWifiInfoBagImpl(String appUniqueId, List<SimpleWifiInfo> simpleWifiInfoList) {
        this.id = appUniqueId;
        this.list = simpleWifiInfoList;
    }

    @Override
    public List<SimpleWifiInfo> getList() {
        return list;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("SimpleWifiInfoBagImpl{id='%s', list=%s}", id, list);
    }
}
