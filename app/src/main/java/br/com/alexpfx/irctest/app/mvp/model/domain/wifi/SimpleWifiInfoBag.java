package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

import java.util.List;

/**
 * Created by alexandre on 22/07/15.
 */
public interface SimpleWifiInfoBag {
    SimpleWifiInfoBag NULL = new SimpleWifiInfoBag() {
        @Override
        public List<SimpleWifiInfo> getList() {
            return null;
        }

        @Override
        public String getId() {
            return null;
        }
    };

    List<SimpleWifiInfo> getList();

    String getId();
}
