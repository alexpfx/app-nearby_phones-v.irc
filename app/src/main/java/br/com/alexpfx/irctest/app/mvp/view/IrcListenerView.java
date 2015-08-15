package br.com.alexpfx.irctest.app.mvp.view;

import br.com.alexpfx.android.lib.network.wifi.SimpleWifiInfoBag;

/**
 * Created by alexandre on 22/07/15.
 */
public interface IrcListenerView {
    void showRegisterSuccess();

    void showWifiReceivedFromIrc(String channel, String user, SimpleWifiInfoBag wifiBag);
}
