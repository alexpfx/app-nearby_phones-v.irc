package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import android.util.Log;
import br.com.alexpfx.irctest.app.BusProvider;
import br.com.alexpfx.irctest.app.irc.WifiReceivedEvent;
import br.com.alexpfx.irctest.app.mvp.model.ChannelInfo;
import br.com.alexpfx.irctest.app.mvp.model.interactor.*;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.ThreadExecutor;
import com.squareup.otto.Subscribe;

import java.util.Date;

/**
 * Created by alex on 12/07/2015.
 */
public class HandshakeUseCaseImpl implements HandshakeUseCase, JoinChannelUseCase.Callback {

    JoinChannelUseCase joinChannelUseCase;
    NotifyUsersUseCase notifyUsersUseCase;
    SendMessageUseCase sendMessageUseCase;
    ListenToIrcUseCaseImpl listenToIrcUseCase;
    PostResultsUseCase postResultsUseCase;
    ThreadExecutor threadExecutor = ThreadExecutor.ThreadExecutorSingleton.INSTANCE.get();
    private String channel;
    private Callback callback;
    {
        BusProvider.INSTANCE.get().register(this);
    }

    @Override
    public void execute(String channel, Callback callback) {
        this.channel = channel;
        this.callback = callback;
        joinChannelUseCase = new JoinChannelUseCaseImpl();
        notifyUsersUseCase = new NotifyUsersUseCaseImpl();
        sendMessageUseCase = new SendMessageUseCaseImpl();
        listenToIrcUseCase = new ListenToIrcUseCaseImpl();
        postResultsUseCase = new PostResultsUseCaseImpl();
        threadExecutor.run(this);
    }

    @Override
    public void run() {
        joinChannelUseCase.execute(channel, this);
    }

    @Override
    public void onJoinChannelSuccess(ChannelInfo channelInfo) {
        Log.d("joined", channelInfo.getChannelName());
    }

    @Override
    public void onJoinChannelFail(Throwable t) {

    }

    @Subscribe
    public void onWifiReceived(WifiReceivedEvent wifiReceived) {
        if (postResultsUseCase == null){
            return;
        }
        postResultsUseCase.execute("appid", channel, wifiReceived.getWifiList(), new Date());
        Log.d("wifiReceived", wifiReceived.toString());

    }
}
