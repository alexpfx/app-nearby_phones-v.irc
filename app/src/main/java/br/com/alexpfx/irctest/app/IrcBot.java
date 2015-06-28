package br.com.alexpfx.irctest.app;

import org.jibble.pircbot.PircBot;

/**
 * Created by alexandre on 28/06/15.
 */
public abstract class IrcBot extends PircBot {

    private IrcBotListener ircBotListener = new IrcBotListener.NullListerner();
    private final String tag;
    private UserIdentity representativeUser;

    public IrcBot(String name, String login, String tag) {
        setName(name);
        setLogin(login);
        this.tag = tag;
        representativeUser = new UserIdentity(name, login);
    }

    public void setIrcBotListener(IrcBotListener ircBotListener) {
        this.ircBotListener = ircBotListener;
    }

    @Override
    protected void onConnect() {
        ircBotListener.onIrcBotConnect(tag);
    }

    @Override
    protected void onDisconnect() {
        ircBotListener.onIrcBotDisconnect(tag);
    }

    public UserIdentity getRepresentativeUser() {
        return representativeUser;
    }
}
