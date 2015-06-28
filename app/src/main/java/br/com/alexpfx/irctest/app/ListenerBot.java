package br.com.alexpfx.irctest.app;

import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

/**
 * Created by alexandre on 28/06/15.
 */
public class ListenerBot extends IrcBot {
    public static final String NAME = "ListenerClient";
    public static final String LOGIN = "ListenerName";
    public static final String TAG = ListenerBot.class.getSimpleName();

    private UserIdentity walkerIdentity;

    public ListenerBot(IrcBotListener listener) {
        super(NAME, LOGIN, TAG);
        setIrcBotListener(listener);
        setVerbose(true);
    }

    public void setWalkerIdentity(UserIdentity walkerIdentity) {
        this.walkerIdentity = walkerIdentity;
    }

    @Override
    protected void onPrivateMessage(String sender, String login, String hostname, String message) {
        if (!isFromWalker(sender, login)) {
            return;
        }
        if (isEncodedMessage (message)){


        }
    }

    private boolean isEncodedMessage(String message) {
        return message.startsWith(walkerIdentity.getMessagePrefix());
    }

    private boolean isFromWalker(String sender, String login) {
        final UserIdentity userIdentity = new UserIdentity(sender, login);
        return userIdentity.equals(walkerIdentity);
    }
}
