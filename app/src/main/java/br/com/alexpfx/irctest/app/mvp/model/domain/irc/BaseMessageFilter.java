package br.com.alexpfx.irctest.app.mvp.model.domain.irc;

import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.SimpleWifiInfoBag;

/**
 * Created by alexandre on 22/07/15.
 */
public abstract class BaseMessageFilter implements MessageFilter {

    @Override
    public final void rawMessage(String channel, String user, String message) {
        final SimpleWifiInfoBag extract = extract(channel, user, message);
        if (extract != SimpleWifiInfoBag.NULL) {
            filteredMessage(channel, user, message, extract);
        }
    }
}
