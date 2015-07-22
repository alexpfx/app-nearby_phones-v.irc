package br.com.alexpfx.irctest.app.mvp.presenters;

/**
 * Created by alexandre on 22/07/15.
 */
public interface IrcListenerPresenter {
    void register(String appUid);

    void unregister();
}
