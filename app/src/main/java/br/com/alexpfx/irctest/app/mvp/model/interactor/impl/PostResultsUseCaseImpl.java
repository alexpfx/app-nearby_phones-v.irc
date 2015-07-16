package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import br.com.alexpfx.irctest.app.WifiList;
import br.com.alexpfx.irctest.app.mvp.model.interactor.PostResultsUseCase;

import java.util.Date;

/**
 * Created by alex on 16/07/2015.
 */
public class PostResultsUseCaseImpl implements PostResultsUseCase {
    private String id;
    private String channel;
    private WifiList list;
    private Date eventTime;

    @Override
    public void execute(String id, String channel, WifiList list, Date eventTime) {
        this.id = id;
        this.channel = channel;
        this.list = list;
        this.eventTime = eventTime;
    }

    @Override
    public void run() {


    }
}
