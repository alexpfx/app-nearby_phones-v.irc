package br.com.alexpfx.irctest.app.irc;

import br.com.alexpfx.irctest.app.WifiList;

/**
 * Created by alex on 12/07/2015.
 */
public class WifiReceived {

    public WifiReceived(WifiList wifiList) {
        this.wifiList = wifiList;
    }

    private WifiList wifiList;


    @Override
    public String toString() {
       return wifiList.toString();
    }
}
