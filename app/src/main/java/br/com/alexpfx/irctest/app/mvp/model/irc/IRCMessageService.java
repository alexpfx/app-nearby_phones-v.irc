package br.com.alexpfx.irctest.app.mvp.model.irc;

/**
 * Created by alexandre on 02/07/15.
 */
public interface IRCMessageService {
    void sendMessage(String target, String message);

    void registerListener(MessageListener m);
}
