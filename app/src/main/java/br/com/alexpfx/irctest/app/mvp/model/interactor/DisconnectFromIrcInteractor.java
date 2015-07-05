package br.com.alexpfx.irctest.app.mvp.model.interactor;

/**
 * Created by alexandre on 05/07/15.
 */
public interface DisconnectFromIrcInteractor {
    void execute(String quitMessage, Callback callback);


    interface Callback {
        void onSucess ();
        void onFailure (Throwable e);
    }


}
