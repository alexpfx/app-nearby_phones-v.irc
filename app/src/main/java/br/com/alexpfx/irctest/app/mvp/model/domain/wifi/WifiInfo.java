package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

/**
 * Created by alexandre on 28/06/15.
 */
public class WifiInfo {
    private final String bssid;
    private String ssid;
    private int rssid;

    private WifiInfo(String bssid, String ssid, int rssid) {
        this.bssid = bssid;
        this.ssid = ssid;
        this.rssid = rssid;
    }

    public static WifiInfo newInstance(String bssid, String ssid, int rssid) {
        return new WifiInfo(bssid, ssid, rssid);
    }

    public String getBssid() {
        return bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getRssid() {
        return rssid;
    }

    public void setRssid(int rssid) {
        this.rssid = rssid;
    }

    public static class SimpleWifiInfo {
        public String b;
        public int r;
    }

}
