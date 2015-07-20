package br.com.alexpfx.irctest.app.mvp.model.irc;

/**
 * Created by alexandre on 02/07/15.
 */
public interface IRCChannelService {

    void join(String channe, AttemptCallback<ChannelObject> callback);

    void leave(String channel);
}
