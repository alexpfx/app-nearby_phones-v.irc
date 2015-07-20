package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl;

import br.com.alexpfx.irctest.app.mvp.model.domain.irc.UserIdentity;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.NotifyUsersUseCase;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.Executor;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.MainThread;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.MainThreadImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.ThreadExecutor;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.utils.IRCApiSingleton;
import com.ircclouds.irc.api.IRCApi;

import java.util.List;

/**
 * Created by alexandre on 07/07/15.
 */
public class NotifyUsersUseCaseImpl implements NotifyUsersUseCase {

    private IRCApi ircApi = IRCApiSingleton.INSTANCE.get();
    private MainThread mainThread = MainThreadImpl.MainThreadSingleton.INSTANCE.get();
    private Executor executor = ThreadExecutor.ThreadExecutorSingleton.INSTANCE.get();

    private List<UserIdentity> userList;
    private Callback callback;

    @Override
    public void execute(List<UserIdentity> userList, Callback callback) {
        this.userList = userList;
        this.callback = callback;
        executor.run(this);
    }

    @Override
    public void run() {
        for (UserIdentity u : userList) {
            ircApi.message(u.getNickname(), "cachorro quente...? ");
        }
    }
}
