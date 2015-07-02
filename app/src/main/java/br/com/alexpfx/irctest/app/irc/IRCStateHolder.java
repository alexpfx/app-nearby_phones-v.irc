package br.com.alexpfx.irctest.app.irc;

import com.ircclouds.irc.api.state.IIRCState;

/**
 * Created by alexandre on 02/07/15.
 */
public class IRCStateHolder {
    private IIRCState state;

    public IRCStateHolder(IIRCState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state == null ? "" : state.getServer().toString();
    }
}
