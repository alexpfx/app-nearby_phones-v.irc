package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl;

import br.com.alexpfx.irctest.app.mvp.model.domain.executor.ThreadExecutor;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.IrcChannelMessageListenerImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.RegisterAsListenerUseCase;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.utils.IRCApiSingleton;
import com.ircclouds.irc.api.IRCApi;

/**
 * Created by alexandre on 22/07/15.
 */
public class RegisterAsListenerUseCaseImpl implements RegisterAsListenerUseCase {
    private IRCApi ircApi = IRCApiSingleton.INSTANCE.get();
    private Callback callback;
    private ThreadExecutor threadExecutor = ThreadExecutor.ThreadExecutorSingleton.INSTANCE.get();

    @Override
    public void run() {
        IrcChannelMessageListenerImpl listener = new IrcChannelMessageListenerImpl();
        ircApi.addListener(listener);
        callback.onRegisterSuccess(listener);
    }

    @Override
    public void register(Callback callback) {
        this.callback = callback;
        threadExecutor.run(this);
    }

}
