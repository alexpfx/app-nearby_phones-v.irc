package br.com.alexpfx.irctest.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 28/06/15.
 */
public class WalkerBot extends IrcBot implements WifiListener.WifiNetworkInfoReceiveListener {

    private List<UserIdentity> walkerListeners = new ArrayList<UserIdentity>();

    public WalkerBot(String userName, String login, String server) {
        super(userName, login, server);
        setVerbose(true);
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
