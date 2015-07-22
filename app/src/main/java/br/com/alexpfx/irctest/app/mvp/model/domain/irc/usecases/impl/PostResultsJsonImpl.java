package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl;

import br.com.alexpfx.irctest.app.mvp.model.domain.executor.ThreadExecutor;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.PostResultsUseCase;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.utils.IRCApiSingleton;
import br.com.alexpfx.irctest.app.mvp.model.domain.json.WifiInfoJsonConverter;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.SimpleWifiInfo;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.SimpleWifiInfoBag;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiList;
import com.ircclouds.irc.api.IRCApi;

import java.util.Date;
import java.util.List;

/**
 * Created by alex on 16/07/2015.
 */
public class PostResultsJsonImpl implements PostResultsUseCase {
    private String id;
    private String channel;
    private WifiList list;
    private Date eventTime;
    private IRCApi api = IRCApiSingleton.INSTANCE.get();
    private ThreadExecutor threadExecutor = ThreadExecutor.ThreadExecutorSingleton.INSTANCE.get();
    private WifiInfoJsonConverter wifiInfoJsonConverter;
    private int SPLIT_LIST_SIZE = 10;

    public PostResultsJsonImpl(WifiInfoJsonConverter wifiInfoJsonConverter) {
        this.wifiInfoJsonConverter = wifiInfoJsonConverter;
    }

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

        final List<List<SimpleWifiInfo>> splitedSimpleInfoList = list.getSplitedSimpleInfoList(SPLIT_LIST_SIZE);
        for (List<SimpleWifiInfo> simpleWifiInfos : splitedSimpleInfoList) {
            SimpleWifiInfoBag s = new SimpleWifiInfoBag(id, simpleWifiInfos);
            final String jsonString = this.wifiInfoJsonConverter.toJson(s);
            api.message("#" + channel, jsonString);
        }
    }
}
