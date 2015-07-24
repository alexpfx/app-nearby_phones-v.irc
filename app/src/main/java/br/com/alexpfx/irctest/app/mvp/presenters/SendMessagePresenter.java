package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiInfoBag;

import java.util.Date;

/**
 * Created by alexandre on 21/07/15.
 */
public interface SendMessagePresenter {
    void sendWifiList(WifiInfoBag wifiInfoBag, String id, String channel, Date date);
}
