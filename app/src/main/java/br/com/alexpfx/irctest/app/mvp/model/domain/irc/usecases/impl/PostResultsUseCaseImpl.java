package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl;

import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.PostResultsUseCase;
import br.com.alexpfx.irctest.app.mvp.model.domain.executor.ThreadExecutor;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.utils.IRCApiSingleton;
import br.com.alexpfx.irctest.app.mvp.model.domain.json.Json;
import br.com.alexpfx.irctest.app.mvp.model.domain.json.impl.GsonJsonImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiInfo;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiList;
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
    private Json json = new GsonJsonImpl();

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
        final String jsonString = this.json.toJson(list.getWifiInfoList());
        StringBuilder sb = new StringBuilder();
        sb.append(jsonString);
        api.message("#" + channel, sb.toString());

    }
}
