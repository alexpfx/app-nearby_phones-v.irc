package br.com.alexpfx.irctest.app;

/**
 * Created by alexandre on 28/06/15.
 */
public class WifiInfo {
    private String bssid;
    private String ssid;
    private int rssid;

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
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
}
