package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import br.com.alexpfx.irctest.app.WifiInfo;
import br.com.alexpfx.irctest.app.WifiList;
import br.com.alexpfx.irctest.app.irc.IRCApiSingleton;
import br.com.alexpfx.irctest.app.mvp.model.interactor.PostResultsUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.ThreadExecutor;
import com.ircclouds.irc.api.IRCApi;

import java.util.Date;

/**
 * Created by alex on 16/07/2015.
 */
public class PostResultsUseCaseImpl implements PostResultsUseCase {
    private String id;
    private String channel;
    private WifiList list;
    private Date eventTime;
    private IRCApi api = IRCApiSingleton.INSTANCE.get();
    private ThreadExecutor threadExecutor = ThreadExecutor.ThreadExecutorSingleton.INSTANCE.get();

    @Override
    public void execute(String id, String channel, WifiList list, Date eventTime) {
        this.id = id;
        this.channel = channel;
        this.list = list;
        this.eventTime = eventTime;
        threadExecutor.run(this);
    }

    @Override
    public void run() {
        for (WifiInfo wifiInfo : list.getWifiInfoList()) {
            StringBuilder sb = new StringBuilder();
            sb.append(id).append(";");
            sb.append(wifiInfo.getBssid()).append(";");
            sb.append(wifiInfo.getRssid()).append(";");
            sb.append(wifiInfo.getSsid()).append(";");
            api.message("#" + channel, sb.toString());
        }

    }
}
