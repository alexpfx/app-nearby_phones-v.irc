package br.com.alexpfx.irctest.app;

/**
 * Created by alexandre on 28/06/15.
 */
public class ReceiverBot extends IrcBot implements WifiScanBroadcastReceiver.WifiNetworkInfoReceiveListener {

    public static final String TAG = ReceiverBot.class.getSimpleName();

    private WifiList wifiList = new WifiList();
    private ReceiverBotListener receiverBotListener;

    public ReceiverBot(String userName, String userLogin, String server) {
        super(userName, userLogin, server);
        setVerbose(false);
    }

    @Override
    protected void onAction(String sender, String login, String hostname, String target, String action) {
        if (!NetAddressUtils.isValidMACAddress(action)) {
            return;
        }
        final WifiInfo byBssid = wifiList.getByBssid(action);
        if (byBssid == null) {
            match(" - ", 0);
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
