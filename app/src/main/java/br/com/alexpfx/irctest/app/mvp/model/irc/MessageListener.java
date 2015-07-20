package br.com.alexpfx.irctest.app.mvp.model.irc;

/**
 * Created by alexandre on 02/07/15.
 */
public interface MessageListener {

    void onPrivateMessage(String user, String message);

    void onMessage(String channel, String user, String message);

    void onQuit(String message);

}
