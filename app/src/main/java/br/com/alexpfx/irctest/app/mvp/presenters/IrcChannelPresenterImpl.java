package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.android.lib.network.irc.ChannelInfo;
import br.com.alexpfx.android.lib.network.irc.interactor.JoinChannelUseCase;
import br.com.alexpfx.android.lib.network.irc.interactor.JoinChannelUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.view.ChannelView;

/**
 * Created by alexandre on 05/07/15.
 */
public class IrcChannelPresenterImpl implements IrcChannelPresenter, JoinChannelUseCase.Callback {
    private JoinChannelUseCase joinChannelUseCase = new JoinChannelUseCaseImpl();
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
        channelView.showChannelJoined(channelInfo.getChannelName());
    }

    @Override
    public void onJoinChannelFail(Throwable t) {
        channelView.showChannelJoinError(t.getMessage());
    }

}
