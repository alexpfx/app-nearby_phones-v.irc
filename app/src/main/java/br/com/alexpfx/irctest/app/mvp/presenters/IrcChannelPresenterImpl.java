package br.com.alexpfx.irctest.app.mvp.presenters;

import android.util.Log;
import br.com.alexpfx.irctest.app.TextLogUtils;
import br.com.alexpfx.irctest.app.mvp.model.ChannelInfo;
import br.com.alexpfx.irctest.app.mvp.model.interactor.JoinChannelUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.ListenToIrcUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.NotifyUsersUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.impl.JoinChannelUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.model.interactor.impl.ListenToIrcUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.model.interactor.impl.NotifyUsersUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.view.ChannelView;

/**
 * Created by alexandre on 05/07/15.
 */
public class IrcChannelPresenterImpl implements IrcChannelPresenter, JoinChannelUseCase.Callback, NotifyUsersUseCase.Callback, ListenToIrcUseCase.Callback {
    private JoinChannelUseCase joinChannelUseCase = new JoinChannelUseCaseImpl();
    private NotifyUsersUseCase notifyUsersUseCase = new NotifyUsersUseCaseImpl();
    private ListenToIrcUseCase listenToIrcUseCase = new ListenToIrcUseCaseImpl();

    private ChannelView channelView;
    private String tag = IrcChannelPresenterImpl.class.getSimpleName();

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
        listenToIrcUseCase.registerListener("handshake", this);
        notifyUsersUseCase.execute(channelInfo.getUsers(), this);


    }

    @Override
    public void onJoinChannelFail(Throwable t) {
        channelView.showChannelJoinError(t.getMessage());
    }

    @Override
    public void onPrivateMessage(String sender, String message) {
        Log.d(tag, TextLogUtils.concat(":", sender, message));
    }
}
