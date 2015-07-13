package br.com.alexpfx.irctest.app;

import com.squareup.otto.Bus;

/**
 * Created by alex on 12/07/2015.
 */
public enum BusProvider {
    INSTANCE;

    private Bus bus = new Bus();

    public Bus get() {
        return bus;
    }


}
