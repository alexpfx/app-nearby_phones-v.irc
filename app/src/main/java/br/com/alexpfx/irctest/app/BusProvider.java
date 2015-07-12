package br.com.alexpfx.irctest.app;

import com.squareup.otto.Bus;

/**
 * Created by alex on 12/07/2015.
 */
public enum BusProvider {
    INSTANCE;

    private static Bus bus = new Bus();

    public static Bus get() {
        return bus;
    }

}
