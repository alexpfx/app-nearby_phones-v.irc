package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases;

import br.com.alexpfx.irctest.app.mvp.model.domain.irc.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.UserIdentity;

/**
 * Created by alexandre on 05/07/15.
 */
public interface IrcConnectUseCase {

    void execute(UserIdentity userIdentity, ServerIdentity serverIdentity, Callback callback);

    interface Callback {
        void onConnectionSuccess();

        void onConnectionFailure(Throwable exception);
    }

}
