package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases;

import br.com.alexpfx.android.lib.base.mvpbase.executor.Interactor;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.WifiInfoBag;

import java.util.Date;

/**
 * Created by alex on 16/07/2015.
 */
public interface PostResultsUseCase extends Interactor {
    void execute(String id, String channel, WifiInfoBag list, Date eventTime);

    interface Callback {

    }
}
