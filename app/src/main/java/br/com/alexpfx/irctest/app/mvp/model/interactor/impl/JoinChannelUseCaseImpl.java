package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import br.com.alexpfx.irctest.app.irc.IRCApiSingleton;
import br.com.alexpfx.irctest.app.mvp.model.ChannelInfo;
import br.com.alexpfx.irctest.app.mvp.model.interactor.JoinChannelUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.Executor;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.MainThread;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.MainThreadImpl;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.ThreadExecutor;
import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.domain.IRCChannel;

/**
 * Created by alexandre on 05/07/15.
 */
public class JoinChannelUseCaseImpl implements JoinChannelUseCase {
    private Executor executor = ThreadExecutor.ThreadExecutorSingleton.INSTANCE.get();
    private IRCApi ircApi = IRCApiSingleton.INSTANCE.get();
    private MainThread mainThread = MainThreadImpl.MainThreadSingleton.INSTANCE.get();
    private String channel;
    private Callback callback;

    @Override
    public void execute(String channel, Callback callback) {
        this.channel = channel;
        this.callback = callback;
        executor.run(this);
    }

    @Override
    public void run() {
        ircApi.joinChannel(channel, new com.ircclouds.irc.api.Callback<IRCChannel>() {
            @Override
            public void onSuccess(IRCChannel aObject) {
                notifySuccess();
            }

            @Override
            public void onFailure(Exception aExc) {
                notifyFail(aExc);
            }
        });
    }

    private void notifyFail(final Exception aExc) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(aExc);
            }
        });

    }

    private void notifySuccess() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(new ChannelInfo() {

                });
            }
        });

    }
}
