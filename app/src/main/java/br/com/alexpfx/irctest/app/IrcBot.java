package br.com.alexpfx.irctest.app;

import org.jibble.pircbot.PircBot;

/**
 * Created by alexandre on 28/06/15.
 */
public abstract class IrcBot extends PircBot {

    protected final UserIdentity userIdentity;
    private IrcBotListener ircBotListener = new IrcBotListener.NullListerner();

    public IrcBot(String name, String login) {
        setName(name);
        setLogin(login);
        userIdentity = UserIdentity.newInstance(name, login);
    }

    public void setIrcBotListener(IrcBotListener ircBotListener) {
        this.ircBotListener = ircBotListener;
    }

    @Override
    protected void onConnect() {
        ircBotListener.onIrcBotConnect(this);
    }

    @Override
    protected void onDisconnect() {
        ircBotListener.onIrcBotDisconnect(this);
    }

    public UserIdentity getUserIdentity() {
        return userIdentity;
    }
}
