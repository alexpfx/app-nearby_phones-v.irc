package br.com.alexpfx.irctest.app.mvp.model.irc;

import com.ircclouds.irc.api.domain.IRCChannel;
import com.ircclouds.irc.api.domain.IRCUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 02/07/15.
 */
public class ChannelObject implements Channel {
    private IRCChannel ircChannel;

    public ChannelObject(IRCChannel ircChannel) {
        this.ircChannel = ircChannel;
    }

    @Override
    public String getName() {
        return ircChannel.getName();
    }

    @Override
    public String getTopicName() {
        return ircChannel.getTopic().getValue();
    }

    @Override
    public String getTopicAutor() {
        return ircChannel.getTopic().getValue();
    }

    @Override
    public List<String> getUsers() {
        List<String> users = new ArrayList<String>();
        for (IRCUser ircUser : ircChannel.getUsers()) {
            users.add(ircUser.getNick());
        }
        return users;
    }

}
