package br.com.alexpfx.irctest.app;

import android.os.ParcelUuid;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by alexandre on 28/06/15.
 */
public class WalkerBot extends IrcBot implements WifiListener.WifiNetworkInfoReceiveListener {

    public static final String NAME = "WalkerClientX";
    public static final String LOGIN = "WalkerNameX";
    public static final String TAG = WalkerBot.class.getSimpleName();

    private List<UserIdentity> walkerListeners = new ArrayList<UserIdentity>();

    public WalkerBot(IrcBotListener listener) {
        super(NAME, LOGIN);
        setIrcBotListener(listener);
        setVerbose(true);
    }

    @Override
    public void receive(String bssid, String ssid, int rssid) {
        if (walkerListeners.isEmpty()){
            return;
        }
        Log.i(TAG, "received");
        for (UserIdentity u:walkerListeners){
            sendAction(u.getName(), "action");
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