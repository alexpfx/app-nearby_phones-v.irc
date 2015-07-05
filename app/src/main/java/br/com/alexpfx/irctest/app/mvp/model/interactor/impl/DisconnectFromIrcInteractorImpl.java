package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import br.com.alexpfx.irctest.app.irc.IRCApiSingleton;
import br.com.alexpfx.irctest.app.mvp.model.interactor.DisconnectFromIrcInteractor;
import br.com.alexpfx.irctest.app.mvp.model.interactor.Interactor;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.ThreadExecutor;
import com.ircclouds.irc.api.ApiException;
import com.ircclouds.irc.api.IRCApi;

import static br.com.alexpfx.irctest.app.mvp.model.interactor.executor.ThreadExecutor.ThreadExecutorSingleton;

/**
 * Created by alexandre on 05/07/15.
 */
public class DisconnectFromIrcInteractorImpl implements DisconnectFromIrcInteractor, Interactor {

    private IRCApi ircApi = IRCApiSingleton.INSTANCE.get();
    private ThreadExecutor executor = ThreadExecutorSingleton.INSTANCE.get();
    private String quitMessage;
    private Callback callback;

    @Override
    public void execute(String quitMessage, Callback callback) {
        this.quitMessage = quitMessage;
        this.callback = callback;
        executor.run(this);
    }

    @Override
    public void run() {
        try {
            ircApi.disconnect(quitMessage);
            callback.onSucess();
        } catch (ApiException e) {
            callback.onFailure(e);

        }
    }
}
