package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import br.com.alexpfx.irctest.app.mvp.model.ChannelInfo;
import br.com.alexpfx.irctest.app.mvp.model.interactor.*;

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
}
