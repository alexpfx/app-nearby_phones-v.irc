package br.com.alexpfx.irctest.app;

import android.util.Log;

import static br.com.alexpfx.irctest.app.TextLogUtils.concat;

/**
 * Created by alexandre on 28/06/15.
 */
public class ReceiverBot extends IrcBot implements WifiListener.WifiNetworkInfoReceiveListener {
    public static final String USER_NAME = "xapifafapofa";
    public static final String USER_LOGIN = "fajfpafoap";

    public static final String TAG = ReceiverBot.class.getSimpleName();

    private WifiList wifiList = new WifiList();

    public ReceiverBot() {
        super(USER_NAME, USER_LOGIN);
        setVerbose(true);
    }

    @Override
    protected void onPrivateMessage(String sender, String login, String hostname, String message) {
        if (!isFromWalker(sender, login)) {
            return;
        }
        if (isEncodedMessage(message)) {

        }
    }

    private boolean isEncodedMessage(String message) {
        return true;
    }

    private boolean isFromWalker(String sender, String login) {
        return true;
    }

    @Override
    protected void onAction(String sender, String login, String hostname, String target, String action) {
        Log.i(TAG,
                concat(" : ", "sender", sender, "login", login, "hostname", hostname, "target", target, "action", action));
    }

    @Override
    public void receive(String bssid, String ssid, int rssid) {
        wifiList.contains(bssid);
        WifiInfo w = new WifiInfo();
        w.setBssid(bssid);
        w.setSsid(ssid);
        w.setRssid(rssid);
        wifiList.add(w);
    }

}
