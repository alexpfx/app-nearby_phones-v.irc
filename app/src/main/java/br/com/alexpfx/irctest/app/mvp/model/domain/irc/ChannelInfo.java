package br.com.alexpfx.irctest.app.mvp.model.domain.irc;

import br.com.alexpfx.irctest.app.mvp.model.domain.irc.UserIdentity;

import java.util.List;

/**
 * Created by alexandre on 05/07/15.
 */
public interface ChannelInfo {
    String getChannelName();

    List<UserIdentity> getUsers();

}
