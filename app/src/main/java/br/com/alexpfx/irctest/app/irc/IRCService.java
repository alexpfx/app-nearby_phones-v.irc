package br.com.alexpfx.irctest.app.irc;

/**
 * Created by alexandre on 02/07/15.
 */
public interface IRCService {
    void connect(IRCConnParameters ircConnParameters, AttemptCallback<IRCStateHolder> callback);

    void join(String channe, AttemptCallback<IRCChannelHolder> callback);
}
