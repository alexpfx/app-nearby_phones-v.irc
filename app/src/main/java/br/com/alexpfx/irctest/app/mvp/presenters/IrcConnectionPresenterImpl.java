package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.mvp.model.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentify;
import br.com.alexpfx.irctest.app.mvp.model.interactor.ConnectToIrc;
import br.com.alexpfx.irctest.app.mvp.model.interactor.ConnectToIrcInteractor;
import br.com.alexpfx.irctest.app.mvp.view.IrcConnectionView;

/**
 * Created by alexandre on 05/07/15.
 */
public class IrcConnectionPresenterImpl implements IrcConnectionPresenter {
    private IrcConnectionView ircConnectionView;
    private ConnectToIrcInteractor connectToIrcInteractor;

    public IrcConnectionPresenterImpl(IrcConnectionView ircConnectionView) {
        this.ircConnectionView = ircConnectionView;
        this.connectToIrcInteractor = new ConnectToIrcInteractor();
    }

    @Override
    public void connect(UserIdentify userIdentity, ServerIdentity serverIdentity) {
        connectToIrcInteractor.execute(userIdentity, serverIdentity, new ConnectToIrc.Callback() {
            @Override
            public void onSuccess() {
                ircConnectionView.showConnectedToIrc();
            }

            @Override
            public void onFailure(Throwable exception) {
                ircConnectionView.showConnectionError(exception.getMessage());
            }
        });
    }

    @Override
    public void disconnect() {

    }
}
