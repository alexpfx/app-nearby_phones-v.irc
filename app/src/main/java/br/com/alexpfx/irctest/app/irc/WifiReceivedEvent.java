package br.com.alexpfx.irctest.app.irc;

import br.com.alexpfx.irctest.app.WifiList;

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
