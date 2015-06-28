package br.com.alexpfx.irctest.app;

import org.jibble.pircbot.PircBot;

/**
 * Created by alexandre on 28/06/15.
 */
public class WalkerBot extends IrcBot implements WifiListener.WifiNetworkInfoReceiveListener{

    public static final String NAME = "WalkerClient";
    public static final String LOGIN = "WalkerName";
    public static final String TAG = WalkerBot.class.getSimpleName();

    public UserIdentity listerUserIdentity;
    private UserIdentity thisUserIdentity;

    public WalkerBot(IrcBotListener listener) {
        super(NAME, LOGIN, TAG);
        setIrcBotListener(listener);
        setVerbose(true);
    }

    @Override
    public void receive(String bssid, String ssid) {
        if (listerUserIdentity == null){
            return;
        }
        final String name = listerUserIdentity.getName();


    }
}
