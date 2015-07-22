package br.com.alexpfx.irctest.app.mvp.model.domain.irc;

/**
 * Created by alexandre on 22/07/15.
 */
public interface MessageFilter {

    void setListener(OnFilteredMessageListener listener);

    boolean filter(String channel, String user, String message);

    void rawMessage(String channel, String user, String message);

    void filteredMessage(String channel, String user, String message);

    interface OnFilteredMessageListener {
        void onFilteredMessage(String channel, String user, String message);
    }
}
