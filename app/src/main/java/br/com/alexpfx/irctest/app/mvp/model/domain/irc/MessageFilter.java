package br.com.alexpfx.irctest.app.mvp.model.domain.irc;

import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.SimpleWifiInfoBag;

/**
 * Created by alexandre on 22/07/15.
 */
public interface MessageFilter {

    void setListener(OnFilteredMessageListener listener);

    SimpleWifiInfoBag extract(String channel, String user, String message);

    void rawMessage(String channel, String user, String message);

    void filteredMessage(String channel, String user, String originalMessage, SimpleWifiInfoBag bag);

    interface OnFilteredMessageListener {
        void onFilteredMessage(String channel, String user, String originalMessage, SimpleWifiInfoBag bag);
    }
}
