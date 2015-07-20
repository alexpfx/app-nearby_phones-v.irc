package br.com.alexpfx.irctest.app.mvp.model.domain.irc.utils;

import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.IRCApiImpl;

/**
 * Created by alexandre on 02/07/15.
 */
//TODO: usar injecao de dependencia.
public enum IRCApiSingleton {
    INSTANCE;

    private IRCApi internal = new IRCApiImpl(false);

    public IRCApi get() {
        return internal;
    }

}
