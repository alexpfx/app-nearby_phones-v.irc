package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import br.com.alexpfx.irctest.app.mvp.model.irc.IRCApiSingleton;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentity;
import br.com.alexpfx.irctest.app.mvp.model.interactor.NotifyUsersUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.Executor;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.MainThread;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.MainThreadImpl;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.ThreadExecutor;
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
