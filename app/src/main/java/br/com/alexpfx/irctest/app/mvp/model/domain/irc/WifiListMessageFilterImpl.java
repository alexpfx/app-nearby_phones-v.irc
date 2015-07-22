package br.com.alexpfx.irctest.app.mvp.model.domain.irc;

import br.com.alexpfx.irctest.app.mvp.model.domain.json.WifiInfoJsonConverter;
import br.com.alexpfx.irctest.app.mvp.model.domain.json.impl.GsonWifiInfoJsonConverterImpl;

/**
 * Created by alexandre on 22/07/15.
 */
public class WifiListMessageFilterImpl extends BaseMessageFilter {
    private WifiInfoJsonConverter converter = new GsonWifiInfoJsonConverterImpl();
    private OnFilteredMessageListener listener;

    @Override
    public void setListener(OnFilteredMessageListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean filter(String channel, String user, String message) {
        final Object o = converter.fromJson(message);
        return true;
    }

    @Override
    public void filteredMessage(String channel, String user, String message) {
        if (listener == null) {
            return;
        }
        listener.onFilteredMessage(channel, user, message);
    }
}
