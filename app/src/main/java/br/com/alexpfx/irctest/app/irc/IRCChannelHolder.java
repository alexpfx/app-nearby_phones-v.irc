package br.com.alexpfx.irctest.app.irc;

import com.ircclouds.irc.api.domain.IRCChannel;

/**
 * Created by alexandre on 02/07/15.
 */
public class IRCChannelHolder {
    private IRCChannel ircChannel;

    public IRCChannelHolder(IRCChannel ircChannel) {
        this.ircChannel = ircChannel;
    }
}
