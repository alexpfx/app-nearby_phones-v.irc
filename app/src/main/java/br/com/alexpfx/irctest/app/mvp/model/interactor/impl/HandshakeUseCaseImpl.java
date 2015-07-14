package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import android.util.Log;
import br.com.alexpfx.irctest.app.BusProvider;
import br.com.alexpfx.irctest.app.irc.WifiReceived;
import br.com.alexpfx.irctest.app.mvp.model.ChannelInfo;
import br.com.alexpfx.irctest.app.mvp.model.interactor.HandshakeUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.JoinChannelUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.NotifyUsersUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.SendMessageUseCase;
import com.squareup.otto.Subscribe;

/**
 * Created by alex on 12/07/2015.
 */
public class HandshakeUseCaseImpl implements HandshakeUseCase, JoinChannelUseCase.Callback {

    JoinChannelUseCase joinChannelUseCase;
    NotifyUsersUseCase notifyUsersUseCase;
    SendMessageUseCase sendMessageUseCase;
    ListenToIrcUseCaseImpl listenToIrcUseCase;
    private String channel;
    private Callback callback;

    @Override
    public void execute(String channel, Callback callback) {
        this.channel = channel;
        this.callback = callback;
        joinChannelUseCase = new JoinChannelUseCaseImpl();
        notifyUsersUseCase = new NotifyUsersUseCaseImpl();
        sendMessageUseCase = new SendMessageUseCaseImpl();
        listenToIrcUseCase = new ListenToIrcUseCaseImpl();
        BusProvider.INSTANCE.get().register(this);
    }

    @Override
    public void run() {
        joinChannelUseCase.execute(channel, this);
    }

    @Override
    public void onJoinChannelSuccess(ChannelInfo channelInfo) {

    }

    @Override
    public void onJoinChannelFail(Throwable t) {

    }

    @Subscribe
    public void onWifiReceived(WifiReceived wifiReceived) {

        Log.d("wifiReceived", wifiReceived.toString());

    }
}
