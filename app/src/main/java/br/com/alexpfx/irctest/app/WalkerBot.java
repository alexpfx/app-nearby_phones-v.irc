package br.com.alexpfx.irctest.app;

import br.com.alexpfx.irctest.app.irc.ConnectionAttemptListener;
import br.com.alexpfx.irctest.app.irc.IRCStateHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 28/06/15.
 */
public class WalkerBot extends IrcBot implements WifiListener.WifiNetworkInfoReceiveListener {

    private static WalkerBot instance;

    private List<UserIdentity> walkerListeners = new ArrayList<UserIdentity>();

    private WalkerBot(String userName, String login, String server) {
        super(userName, login, server);
        setVerbose(true);
    }

    public static synchronized WalkerBot getInstance(String userName, String login, String server) {
        if (instance == null) {
            instance = new WalkerBot(userName, login, server);
        }
        return instance;

    }

    @Override
    public void receive(String bssid, String ssid, int rssid) {
        if (walkerListeners.isEmpty()) {
            return;
        }
        for (UserIdentity u : walkerListeners) {
            sendAction(u.getName(), bssid);
        }
    }

    @Override
    protected void onConnect() {
        super.onConnect();
    }

    @Override
    protected void onDisconnect() {
        super.onDisconnect();
    }

    public void addWalkerListener(UserIdentity userIdentity) {
        walkerListeners.add(userIdentity);
    }

    public void removeWalkerListener(UserIdentity userIdentity) {
        walkerListeners.remove(userIdentity);
    }

}
