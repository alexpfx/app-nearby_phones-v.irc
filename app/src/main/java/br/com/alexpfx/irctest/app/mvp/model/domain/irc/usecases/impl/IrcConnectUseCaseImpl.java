package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl;

import android.util.Log;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.UserIdentity;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.Interactor;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.IrcConnectUseCase;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.MainThread;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.MainThreadImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.ThreadExecutor;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.utils.IRCApiSingleton;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.utils.IRCServiceUtils;
import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.state.IIRCState;

/**
 * Created by alexandre on 05/07/15.
 */
public class IrcConnectUseCaseImpl implements Interactor, IrcConnectUseCase {

    private IRCApi ircApi = IRCApiSingleton.INSTANCE.get();

    //TODO: injectar.
    private MainThread mainThread = MainThreadImpl.MainThreadSingleton.INSTANCE.get();

    //TODO: injectar.
    private ThreadExecutor executor = ThreadExecutor.ThreadExecutorSingleton.INSTANCE.get();

    private UserIdentity userIdentity;
    private ServerIdentity serverIdentity;
    private Callback callback;
    private String tag = IrcConnectUseCaseImpl.class.getSimpleName();

    public IrcConnectUseCaseImpl() {
    }

    @Override
    public void execute(UserIdentity userIdentity, ServerIdentity serverIdentity, Callback callback) {
        this.userIdentity = userIdentity;
        this.serverIdentity = serverIdentity;
        this.callback = callback;
        Log.d(tag, "executing connection");
        executor.run(this);
    }

    @Override
    public void run() {
        ircApi.connect(IRCServiceUtils
                .getServerParameters(serverIdentity.getIrcServer(), userIdentity.getEmail(), userIdentity
                        .getName(), userIdentity.getNickname(), userIdentity
                        .getAlternative()), new com.ircclouds.irc.api.Callback<IIRCState>() {
            @Override
            public void onSuccess(IIRCState aObject) {
                notifySucess();
            }

            @Override
            public void onFailure(Exception aExc) {
                notifyFailure(aExc);
            }
        });

    }

    private void notifyFailure(final Exception aExc) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onConnectionFailure(aExc);
            }
        });

    }

    private void notifySucess() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onConnectionSuccess();
            }
        });

    }

}
