package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexandre on 23/07/15.
 */
public final class WifiRepository {

    private List<TimedOut<WifiInfo>> list = new ArrayList<TimedOut<WifiInfo>>();
    private List<TimedOut> toRemove = new ArrayList<TimedOut>();

    public WifiRepository() {

    }

    /* não permite Wifis repetidos. um wifi repetido possui o mesmo BSSID, mesmo que tenha SSID diferentes*/
    /* ver metodo equals de WifiInfo */
    public void add(WifiInfo wifiInfo) {
        cleanUp();
        synchronized (this) {
            TimedOut<WifiInfo> to = new TimedOutObject<WifiInfo>(120, wifiInfo);
            /*sempre remove ele mesmo da lista para: atualizar o timeout, o RSSID e o SSID */
            list.remove(to);
            list.add(to);
        }
    }

    public void addAll(WifiInfoBag list) {
        final Iterator<WifiInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
            final WifiInfo wifiInfo = iterator.next();
            add(wifiInfo);
        }
    }

    private void cleanUp() {
        synchronized (this) {
            for (TimedOut t : list) {
                if (t.isExpired()) {
                    toRemove.add(t);
                }
            }
            list.removeAll(toRemove);
            toRemove.clear();
        }
    }

    public int getSize() {
        return list.size();
    }

}
