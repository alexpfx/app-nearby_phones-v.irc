package br.com.alexpfx.irctest.app.ottobus.events;

import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiList;

/**
 * Created by alex on 12/07/2015.
 */
public class WifiReceivedEvent {

    private WifiList wifiList;

    public WifiReceivedEvent(WifiList wifiList) {
        this.wifiList = wifiList;
    }

    public WifiList getWifiList() {
        return wifiList;
    }

    @Override
    public String toString() {
        return wifiList.toString();
    }
}
