package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.mvp.model.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentify;
import br.com.alexpfx.irctest.app.mvp.model.interactor.ConnectToIrcInteractor;
import br.com.alexpfx.irctest.app.mvp.model.interactor.DisconnectFromIrcInteractor;
import br.com.alexpfx.irctest.app.mvp.model.interactor.impl.ConnectToIrcInteractorImpl;
import br.com.alexpfx.irctest.app.mvp.model.interactor.impl.DisconnectFromIrcInteractorImpl;
import br.com.alexpfx.irctest.app.mvp.view.IrcConnectionView;

/**
 * Created by alexandre on 05/07/15.
 */
public class IrcConnectionPresenterImpl implements IrcConnectionPresenter {

    private IrcConnectionView ircConnectionView;
    private ConnectToIrcInteractor connectToIrcInteractorImpl;
    private DisconnectFromIrcInteractor disconnectFromIrcInteractor;

    public IrcConnectionPresenterImpl(IrcConnectionView ircConnectionView) {
        this.ircConnectionView = ircConnectionView;
        this.connectToIrcInteractorImpl = new ConnectToIrcInteractorImpl();
        this.disconnectFromIrcInteractor = new DisconnectFromIrcInteractorImpl();

    }

    @Override
    public void connect(UserIdentify userIdentity, ServerIdentity serverIdentity) {
        connectToIrcInteractorImpl.execute(userIdentity, serverIdentity, new ConnectToIrcInteractor.Callback() {
            @Override
            public void onSuccess() {
                ircConnectionView.showConnectedToIrc();

            }

            @Override
            public void onFailure(final Throwable exception) {
                ircConnectionView.showConnectionError(exception.getMessage());

            }
        });
    }

    @Override
    public void disconnect() {
        disconnectFromIrcInteractor.execute("bye bye", new DisconnectFromIrcInteractor.Callback() {
            @Override
            public void onSuccess() {
                ircConnectionView.showDisconnected();
            }

            @Override
            public void onFailure(Throwable e) {
                ircConnectionView.showNotConnected();
            }
        });
    }
}
