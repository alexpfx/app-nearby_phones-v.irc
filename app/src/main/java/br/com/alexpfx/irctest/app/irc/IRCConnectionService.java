package br.com.alexpfx.irctest.app.irc;

/**
 * Created by alexandre on 02/07/15.
 */
public interface IRCConnectionService {
    void connect(IRCConnParameters ircConnParameters, AttemptCallback<IRCStateHolder> callback);




}
