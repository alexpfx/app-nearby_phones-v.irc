package br.com.alexpfx.irctest.app.mvp.presenters;

import java.util.Date;

import br.com.alexpfx.android.lib.network.wifi.WifiInfoBag;

/**
 * Created by alexandre on 21/07/15.
 */
public interface SendMessagePresenter {
    void sendWifiList(WifiInfoBag wifiInfoBag, String id, String channel, Date date);
}
