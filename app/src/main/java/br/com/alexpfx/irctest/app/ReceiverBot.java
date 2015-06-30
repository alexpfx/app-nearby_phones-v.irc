package br.com.alexpfx.irctest.app;

import android.util.Log;

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
        setVerbose(false);
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
        if (!NetAddressUtils.isValidMACAddress(action)) {
            return;
        }
        final WifiInfo byBssid = wifiList.getByBssid(action);
        if (byBssid == null) {
            return;
        }
        Log.i(TAG, TextLogUtils.concat(" : ", "ssid", byBssid.getSsid(), "rssid", String.valueOf(byBssid.getRssid())));
    }

    @Override
    public void receive(String bssid, String ssid, int rssid) {
        final WifiInfo byBssid = wifiList.getByBssid(bssid);
        if (byBssid == null) {
            wifiList.add(WifiInfo.newInstance(bssid, ssid, rssid));
        } else {
            byBssid.setRssid(rssid);
            byBssid.setSsid(ssid);
        }
    }

}
