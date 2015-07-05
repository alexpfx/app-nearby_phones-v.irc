package br.com.alexpfx.irctest.app.mvp.model.interactor;

import br.com.alexpfx.irctest.app.mvp.model.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentify;

/**
 * Created by alexandre on 05/07/15.
 */
public interface ConnectToIrc {

    void execute(UserIdentify userIdentity, ServerIdentity serverIdentity, Callback callback);

    interface Callback {
        void onSuccess();

        void onFailure(Throwable exception);
    }

}
