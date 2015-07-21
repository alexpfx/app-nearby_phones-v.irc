package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiList;

import java.util.Date;

/**
 * Created by alexandre on 21/07/15.
 */
public interface SendMessagePresenter {
    void sendWifiList(WifiList wifiList, String id, String channel, Date date);
}
