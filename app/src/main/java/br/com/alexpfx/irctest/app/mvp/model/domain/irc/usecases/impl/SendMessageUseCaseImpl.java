package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl;

import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.SendMessageUseCase;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.Executor;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.MainThread;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.MainThreadImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.ThreadExecutor;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.utils.IRCApiSingleton;
import com.ircclouds.irc.api.IRCApi;

/**
 * Created by alex on 11/07/2015.
 */
public class SendMessageUseCaseImpl implements SendMessageUseCase {
    private IRCApi ircApi = IRCApiSingleton.INSTANCE.get();
    private MainThread mainThread = MainThreadImpl.MainThreadSingleton.INSTANCE.get();
    private Executor executor = ThreadExecutor.ThreadExecutorSingleton.INSTANCE.get();
    private String user;
    private String message;

    @Override
    public void execute(String user, String message) {
        this.user = user;
        this.message = message;
        executor.run(this);
    }

    @Override
    public void run() {

    }
}
