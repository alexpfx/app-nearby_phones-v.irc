package br.com.alexpfx.irctest.app.mvp.model;

import java.util.List;

/**
 * Created by alexandre on 05/07/15.
 */
public interface ChannelInfo {
    String getChannelName();

    List<UserIdentify> getUsers();

}
