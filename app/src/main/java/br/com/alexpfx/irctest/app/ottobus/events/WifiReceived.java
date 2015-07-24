package br.com.alexpfx.irctest.app.ottobus.events;

import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiInfoBag;

/**
 * Created by alex on 12/07/2015.
 */
public class WifiReceived {

    private WifiInfoBag wifiInfoBag;

    public WifiReceived(WifiInfoBag wifiInfoBag) {
        this.wifiInfoBag = wifiInfoBag;
    }

    public WifiInfoBag getWifiInfoBag() {
        return wifiInfoBag;
    }

    @Override
    public String toString() {
        return wifiInfoBag.toString();
    }
}
