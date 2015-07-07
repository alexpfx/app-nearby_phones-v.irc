package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.mvp.model.ChannelInfo;
import br.com.alexpfx.irctest.app.mvp.model.interactor.JoinChannelUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.NotifyUsersUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.impl.JoinChannelUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.model.interactor.impl.NotifyUsersUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.view.ChannelView;

/**
 * Created by alexandre on 05/07/15.
 */
public class IrcChannelPresenterImpl implements IrcChannelPresenter, JoinChannelUseCase.Callback, NotifyUsersUseCase.Callback {
    private JoinChannelUseCase joinChannelUseCase = new JoinChannelUseCaseImpl();
    private NotifyUsersUseCase notifyUsersUseCase = new NotifyUsersUseCaseImpl();

    private ChannelView channelView;

    public IrcChannelPresenterImpl(ChannelView channelView) {
        this.channelView = channelView;
    }

    @Override
    public void join(final String channel) {
        joinChannelUseCase.execute(channel, this);
    }

    @Override
    public void unJoin(String channel) {

    }

    @Override
    public void onJoinChannelSuccess(ChannelInfo channelInfo) {
        channelView.showChannelJoined(channelInfo.getChannelName() + channelInfo.getUsers().size());

        notifyUsersUseCase.execute(channelInfo.getUsers(), this);

    }

    @Override
    public void onJoinChannelFail(Throwable t) {
        channelView.showChannelJoinError(t.getMessage());
    }
}
