package br.com.alexpfx.irctest.app.mvp.model.domain.irc;

import android.util.Log;
import br.com.alexpfx.irctest.app.exceptions.JsonFromStringConvertException;
import br.com.alexpfx.irctest.app.mvp.model.domain.json.WifiInfoJsonConverter;
import br.com.alexpfx.irctest.app.mvp.model.domain.json.impl.GsonWifiInfoJsonConverterImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.SimpleWifiInfoBag;
import br.com.alexpfx.irctest.app.utils.TagUtils;

/**
 * Created by alexandre on 22/07/15.
 */
public class WifiListMessageFilterImpl implements MessageFilter {
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
    public void rawMessage(String channel, String user, String message) {
        final SimpleWifiInfoBag simpleWifiInfoBag;
        try {
            simpleWifiInfoBag = converter.fromJson(message, SimpleWifiInfoBag.class);
            if (acceptId(simpleWifiInfoBag.getId())) {
                notifyListener(channel, user, message, simpleWifiInfoBag);
            }
        } catch (JsonFromStringConvertException e) {
            Log.d(TagUtils.tag(), "it's not a json structured message");
        }
    }

    public void notifyListener(String channel, String user, String originalMessage, SimpleWifiInfoBag bag) {
        if (listener != null) {
            listener.onFilteredMessage(channel, user, originalMessage, bag);
        }
    }

    private boolean acceptId(String id) {
        return id != null && !id.equals(appUid);
    }

}
