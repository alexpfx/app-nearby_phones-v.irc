package br.com.alexpfx.irctest.app.irc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 03/07/15.
 */
public class MessageListeners {
    private List<MessageListener> messageListeners = new ArrayList<MessageListener>();

    public void notifyChannelMessage(String channel, String user, String message) {
        for (MessageListener listener : messageListeners) {
            listener.onMessage(channel, user, message);
        }
    }

    public void notifyPrivateMessage(String nick, String message) {
        for (MessageListener listener : messageListeners) {
            listener.onPrivateMessage(nick, message);
        }
    }

    public void notifyQuit(String message) {
        for (MessageListener listener : messageListeners) {
            listener.onQuit(message);
        }
    }

    public void add(MessageListener listener) {
        messageListeners.add(listener);
    }

}
