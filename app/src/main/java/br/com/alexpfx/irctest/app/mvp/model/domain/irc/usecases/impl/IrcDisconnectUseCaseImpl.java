package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl;

import br.com.alexpfx.android.lib.base.mvpbase.executor.MainThread;
import br.com.alexpfx.android.lib.base.mvpbase.executor.MainThreadImpl;
import br.com.alexpfx.android.lib.base.mvpbase.executor.ThreadExecutor;
import br.com.alexpfx.android.lib.base.mvpbase.executor.Interactor;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.IrcDisconnectUseCase;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.utils.IRCApiSingleton;
import com.ircclouds.irc.api.ApiException;
import com.ircclouds.irc.api.IRCApi;

import static br.com.alexpfx.android.lib.base.mvpbase.executor.ThreadExecutor.ThreadExecutorSingleton;

/**
 * Created by alexandre on 05/07/15.
 */
public class IrcDisconnectUseCaseImpl implements IrcDisconnectUseCase, Interactor {

    private MainThread mainThread = MainThreadImpl.MainThreadSingleton.INSTANCE.get();
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
            notifySuccess();
        } catch (ApiException e) {
            notifyFailure(e);

        }
    }

    private void notifyFailure(final ApiException e) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onDisconnectionFailure(e);
            }
        });
    }

    private void notifySuccess() {

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onDisconnectionSuccess();
            }
        });

    }
}
