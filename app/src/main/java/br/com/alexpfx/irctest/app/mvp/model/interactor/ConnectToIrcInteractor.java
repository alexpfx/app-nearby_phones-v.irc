package br.com.alexpfx.irctest.app.mvp.model.interactor;

import br.com.alexpfx.irctest.app.irc.IRCApiSingleton;
import br.com.alexpfx.irctest.app.irc.IRCServiceUtils;
import br.com.alexpfx.irctest.app.mvp.model.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentify;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.Executor;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.ThreadExecutor;
import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.state.IIRCState;

/**
 * Created by alexandre on 05/07/15.
 */
public class ConnectToIrcInteractor implements Interactor, ConnectToIrc {

    private IRCApi ircApi = IRCApiSingleton.INSTANCE.get();

    //TODO: injectar.
    private Executor executor;

    private UserIdentify userIdentity;
    private ServerIdentity serverIdentity;
    private Callback callback;

    public ConnectToIrcInteractor() {
        this.executor = new ThreadExecutor();
    }

    @Override
    public void execute(UserIdentify userIdentity, ServerIdentity serverIdentity, Callback callback) {
        this.userIdentity = userIdentity;
        this.serverIdentity = serverIdentity;
        this.callback = callback;
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
                callback.onSuccess();
            }

            @Override
            public void onFailure(Exception aExc) {
                callback.onFailure(aExc);
            }
        });

    }

}
