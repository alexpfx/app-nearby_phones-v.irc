package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.android.lib.network.irc.ServerIdentity;
import br.com.alexpfx.android.lib.network.irc.UserIdentity;
import br.com.alexpfx.android.lib.network.irc.IrcConnectUseCase;
import br.com.alexpfx.android.lib.network.irc.IrcDisconnectUseCase;
import br.com.alexpfx.irctest.app.mvp.view.IrcConnectionView;

/**
 * Created by alexandre on 05/07/15.
 */
public class IrcConnectionPresenterImpl implements IrcConnectionPresenter, IrcDisconnectUseCase.Callback, IrcConnectUseCase.Callback {

    private final IrcConnectionView ircConnectionView;
    private final IrcConnectUseCase ircConnectUseCase;
    private final IrcDisconnectUseCase ircDisconnectUseCase;

    public IrcConnectionPresenterImpl(IrcConnectionView ircConnectionView, IrcConnectUseCase ircConnectUseCase, IrcDisconnectUseCase ircDisconnectUseCase) {
        this.ircConnectionView = ircConnectionView;
        this.ircConnectUseCase = ircConnectUseCase;
        this.ircDisconnectUseCase = ircDisconnectUseCase;
    }

    @Override
    public void connect(UserIdentity userIdentity, ServerIdentity serverIdentity) {
        ircConnectUseCase.execute(userIdentity, serverIdentity, this);
    }

    @Override
    public void disconnect() {
        ircDisconnectUseCase.execute("bye", this);
    }

    //callbacks

    @Override
    public void onDisconnectionSuccess() {
        ircConnectionView.showDisconnectonSuccess();
    }

    @Override
    public void onDisconnectionFailure(Throwable e) {
        ircConnectionView.showDisconnectionError(e.getMessage());
    }

    @Override
    public void onConnectionSuccess() {
        ircConnectionView.showConnectionSuccess();
    }

    @Override
    public void onConnectionFailure(Throwable exception) {
        ircConnectionView.showConnectionError(exception.getMessage());
    }
}
