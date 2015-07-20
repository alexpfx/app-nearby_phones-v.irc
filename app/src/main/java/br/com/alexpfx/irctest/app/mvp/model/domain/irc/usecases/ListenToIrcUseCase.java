package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases;

/**
 * Created by alexandre on 07/07/15.
 */
public interface ListenToIrcUseCase {

    void registerListener(String filter, Callback callback);

    interface Callback {
        void onPrivateMessage(String sender, String message);

    }

}