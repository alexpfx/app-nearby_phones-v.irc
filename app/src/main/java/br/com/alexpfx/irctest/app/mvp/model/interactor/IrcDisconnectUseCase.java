package br.com.alexpfx.irctest.app.mvp.model.interactor;

/**
 * Created by alexandre on 05/07/15.
 */
public interface IrcDisconnectUseCase {
    void execute(String quitMessage, Callback callback);

    interface Callback {
        void onSuccess();

        void onFailure(Throwable e);
    }

}