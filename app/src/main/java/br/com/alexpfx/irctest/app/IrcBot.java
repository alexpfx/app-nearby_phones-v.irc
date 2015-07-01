package br.com.alexpfx.irctest.app;

import org.jibble.pircbot.PircBot;

/**
 * Created by alexandre on 28/06/15.
 */
public abstract class IrcBot extends PircBot {

    protected final UserIdentity userIdentity;
    private IrcBotListener ircBotListener = new IrcBotListener.NullListerner();
    private BotStarter starter;

    public IrcBot(String name, String login, String server) {
        setName(name);
        setLogin(login);
        userIdentity = UserIdentity.newInstance(name, login);
        starter = new BotStarter(this, server);
    }

    public void connect() {
        starter.connect();
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
