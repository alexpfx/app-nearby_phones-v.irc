package br.com.alexpfx.irctest.app.mvp.model.domain.irc;

import br.com.alexpfx.irctest.app.exceptions.JsonFromStringConvertException;
import br.com.alexpfx.irctest.app.mvp.model.domain.json.WifiInfoJsonConverter;
import br.com.alexpfx.irctest.app.mvp.model.domain.json.impl.GsonWifiInfoJsonConverterImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.SimpleWifiInfoBag;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.SimpleWifiInfoBagImpl;

/**
 * Created by alexandre on 22/07/15.
 */
public class WifiListMessageFilterImpl extends BaseMessageFilter {
    private WifiInfoJsonConverter converter = new GsonWifiInfoJsonConverterImpl();
    private String appUid;

    private OnFilteredMessageListener listener;

    public WifiListMessageFilterImpl(String appUid) {
        this.appUid = appUid;
    }

    @Override
    public void setListener(OnFilteredMessageListener listener) {
        this.listener = listener;
    }

    @Override
    public SimpleWifiInfoBag extract(String channel, String user, String message) {
        final SimpleWifiInfoBagImpl simpleWifiInfoBagImpl;
        try {
            simpleWifiInfoBagImpl = converter.fromJson(message, SimpleWifiInfoBagImpl.class);
            final String id = simpleWifiInfoBagImpl.getId();
            if (!acceptId(id)) {
                return SimpleWifiInfoBag.NULL;
            }
        } catch (JsonFromStringConvertException e) {
            return SimpleWifiInfoBag.NULL;
        }
        return simpleWifiInfoBagImpl;
    }

    @Override
    public void filteredMessage(String channel, String user, String originalMessage, SimpleWifiInfoBag bag) {
        if (listener == null) {
            return;
        }
        listener.onFilteredMessage(channel, user, originalMessage, bag);
    }

    private boolean acceptId(String id) {
        return id != null && !id.equals(appUid);
    }

}
