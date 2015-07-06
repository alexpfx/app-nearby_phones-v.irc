package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.mvp.model.ChannelInfo;
import br.com.alexpfx.irctest.app.mvp.model.interactor.JoinChannelUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.impl.JoinChannelUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.view.ChannelView;

/**
 * Created by alexandre on 05/07/15.
 */
public class IrcChannelPresenterImpl implements IrcChannelPresenter {
    private JoinChannelUseCase channelUseCase = new JoinChannelUseCaseImpl();
    private ChannelView channelView;

    public IrcChannelPresenterImpl(ChannelView channelView) {
        this.channelView = channelView;
    }

    @Override
    public void join(final String channel) {
        channelUseCase.execute(channel, new JoinChannelUseCase.Callback() {
            @Override
            public void onSuccess(ChannelInfo channelInfo) {
                channelView.showChannelJoined("");
            }

            @Override
            public void onFailure(Throwable t) {
                channelView.showChannelJoinError(t.getMessage());

            }
        });
    }

    @Override
    public void unJoin(String channel) {

    }
}
