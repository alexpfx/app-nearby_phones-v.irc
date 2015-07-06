package br.com.alexpfx.irctest.app.mvp.view;

/**
 * Created by alexandre on 05/07/15.
 */
public interface ChannelView {

    void showChannelJoined(String channel);

    void showChannelJoinError(String message);

}
