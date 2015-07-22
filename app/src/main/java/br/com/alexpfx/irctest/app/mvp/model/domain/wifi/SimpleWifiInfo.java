package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

/**
 * Created by alexandre on 22/07/15.
 */

public class SimpleWifiInfo {
    /* bssid */
    private String b;
    /* rssid */
    private int r;

    public SimpleWifiInfo(String bssid, int rssid) {
        this.b = bssid;
        this.r = rssid;
    }

    public String getB() {
        return b;
    }

    public int getR() {
        return r;
    }
}
