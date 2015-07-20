package br.com.alexpfx.irctest.app.mvp.model.interactor;

import br.com.alexpfx.irctest.app.mvp.model.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentity;

/**
 * Created by alexandre on 05/07/15.
 */
public interface IrcConnectUseCase {

    void execute(UserIdentity userIdentity, ServerIdentity serverIdentity, Callback callback);

    interface Callback {
        void onSuccess();

        void onFailure(Throwable exception);
    }

}
