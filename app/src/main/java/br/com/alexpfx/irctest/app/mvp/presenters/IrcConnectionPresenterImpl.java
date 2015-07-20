package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.mvp.model.domain.irc.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.UserIdentity;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.IrcConnectUseCase;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.IrcDisconnectUseCase;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl.IrcConnectUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl.IrcDisconnectUseCaseImpl;
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
    public void connect(UserIdentity userIdentity, ServerIdentity serverIdentity) {
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
