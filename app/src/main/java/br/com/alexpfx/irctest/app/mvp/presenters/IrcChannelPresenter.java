package br.com.alexpfx.irctest.app.mvp.presenters;

/**
 * Created by alexandre on 05/07/15.
 */
public interface IrcChannelPresenter {

    void join(String channel);

    void unJoin(String channel);

}
