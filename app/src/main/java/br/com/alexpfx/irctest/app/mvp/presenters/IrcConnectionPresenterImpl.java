package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.mvp.model.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentify;
import br.com.alexpfx.irctest.app.mvp.model.interactor.IrcConnectUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.IrcDisconnectUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.impl.IrcConnectUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.model.interactor.impl.IrcDisconnectUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.view.IrcConnectionView;

/**
 * Created by alexandre on 05/07/15.
 */
public class IrcConnectionPresenterImpl implements IrcConnectionPresenter {

    private IrcConnectionView ircConnectionView;
    private IrcConnectUseCase ircConnectUseCaseImpl;
    private IrcDisconnectUseCase ircDisconnectUseCase;

    public IrcConnectionPresenterImpl(IrcConnectionView ircConnectionView) {
        this.ircConnectionView = ircConnectionView;
        this.ircConnectUseCaseImpl = new IrcConnectUseCaseImpl();
        this.ircDisconnectUseCase = new IrcDisconnectUseCaseImpl();

    }

    @Override
    public void connect(UserIdentify userIdentity, ServerIdentity serverIdentity) {
        ircConnectUseCaseImpl.execute(userIdentity, serverIdentity, new IrcConnectUseCase.Callback() {
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
        ircDisconnectUseCase.execute("bye bye", new IrcDisconnectUseCase.Callback() {
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
