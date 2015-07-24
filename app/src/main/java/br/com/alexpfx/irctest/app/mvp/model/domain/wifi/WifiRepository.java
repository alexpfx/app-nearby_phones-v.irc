package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 23/07/15.
 */
public final class WifiRepository {

    private List<TimedOut<WifiInfo>> list = new ArrayList<TimedOut<WifiInfo>>();
    private List<TimedOut> toRemove = new ArrayList<TimedOut>();

    public WifiRepository() {

    }

    public void add(WifiInfo wifiInfo) {
        cleanUp();
        synchronized (this) {
            TimedOut<WifiInfo> to = new TimedOutObject<WifiInfo>(120, wifiInfo);
            list.remove(to);
            list.add(to);
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

}
