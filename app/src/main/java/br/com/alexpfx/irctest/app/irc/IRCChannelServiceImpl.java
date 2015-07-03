package br.com.alexpfx.irctest.app.irc;

import com.ircclouds.irc.api.Callback;
import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.IRCApiImpl;
import com.ircclouds.irc.api.domain.IRCChannel;

/**
 * Created by alexandre on 02/07/15.
 */
public class IRCChannelServiceImpl implements IRCChannelService {

    private IRCApi api = new IRCApiImpl(false);

    @Override
    public void join(String channel, final AttemptCallback<ChannelObject> callback) {

        api.joinChannel(channel, new Callback<IRCChannel>() {
            @Override
            public void onSuccess(IRCChannel aObject) {
                callback.onSuccess(new ChannelObject(aObject));
            }

            @Override
            public void onFailure(Exception ex) {
                callback.onFailure(ex);
            }
        });
    }




}
