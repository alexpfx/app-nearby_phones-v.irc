package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.PostResultsUseCase;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiInfoBag;

import java.util.Date;

/**
 * Created by alexandre on 21/07/15.
 */
public class SendMessagePresenterImpl implements SendMessagePresenter {
    private PostResultsUseCase postResultsUseCase;

    public SendMessagePresenterImpl(PostResultsUseCase postResultsUseCase) {
        this.postResultsUseCase = postResultsUseCase;
    }

    @Override
    public void sendWifiList(WifiInfoBag wifiInfoBag, String id, String channel, Date date) {
        postResultsUseCase.execute(id, channel, wifiInfoBag, date);

    }
}
