package br.com.alexpfx.irctest.app.irc;

import br.com.alexpfx.irctest.app.irc.IRCStateHolder;

/**
 * Created by alexandre on 01/07/15.
 */
public interface ConnectionAttemptListener <T> {
    void onSuccess (T ircState);

    void onFailure (Exception exception);
}
