package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases;

/**
 * Created by alexandre on 05/07/15.
 */
public interface IrcDisconnectUseCase {
    void execute(String quitMessage, Callback callback);

    interface Callback {
        void onDisconnectionSuccess();

        void onDisconnectionFailure(Throwable e);
    }

}
