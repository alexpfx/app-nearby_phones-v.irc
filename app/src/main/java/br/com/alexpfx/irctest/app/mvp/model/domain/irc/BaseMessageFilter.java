package br.com.alexpfx.irctest.app.mvp.model.domain.irc;

/**
 * Created by alexandre on 22/07/15.
 */
public abstract class BaseMessageFilter implements MessageFilter {

    @Override
    public final void rawMessage(String channel, String user, String message) {
        if (filter(channel, user, message)) {
            filteredMessage(channel, user, message);
        }
    }
}
