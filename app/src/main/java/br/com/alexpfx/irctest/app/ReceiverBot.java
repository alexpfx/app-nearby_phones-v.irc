package br.com.alexpfx.irctest.app;

/**
 * Created by alexandre on 28/06/15.
 */
public class ReceiverBot extends IrcBot implements WifiListener.WifiNetworkInfoReceiveListener {
    public static final String USER_NAME = "xapifafapofa";
    public static final String USER_LOGIN = "fajfpafoap";

    public static final String TAG = ReceiverBot.class.getSimpleName();

    private WifiList wifiList = new WifiList();
    private ReceiverBotListener receiverBotListener;

    public ReceiverBot() {
        super(USER_NAME, USER_LOGIN);
        setVerbose(false);
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
        match(byBssid.getSsid(), byBssid.getRssid());
    }

    private void match(String ssid, int rssid) {
        if (receiverBotListener != null) {
            receiverBotListener.onMatch(ssid, rssid * -1);
        }
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

    public void setReceiverBotListener(ReceiverBotListener receiverBotListener) {
        this.receiverBotListener = receiverBotListener;
    }

}
