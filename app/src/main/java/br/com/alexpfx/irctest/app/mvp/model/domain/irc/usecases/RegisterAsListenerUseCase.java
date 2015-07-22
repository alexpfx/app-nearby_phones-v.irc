package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases;

import br.com.alexpfx.irctest.app.mvp.model.domain.irc.IrcChannelMessageListener;

/**
 * Created by alexandre on 22/07/15.
 */
public interface RegisterAsListenerUseCase extends Interactor {
    void register(Callback callback);

    interface Callback {
        void onRegisterSuccess(IrcChannelMessageListener listener);
    }
}
