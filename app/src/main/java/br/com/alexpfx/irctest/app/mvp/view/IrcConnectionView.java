package br.com.alexpfx.irctest.app.mvp.view;

/**
 * Created by alexandre on 04/07/15.
 */
public interface IrcConnectionView {

    void showConnectionSuccess();

    void showConnectionError(String message);

    void showDisconnectonSuccess();

    void showDisconnectionError(String message);

}
