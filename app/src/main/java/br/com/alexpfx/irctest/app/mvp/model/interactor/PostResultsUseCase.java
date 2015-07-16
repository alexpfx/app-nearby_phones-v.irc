package br.com.alexpfx.irctest.app.mvp.model.interactor;

import br.com.alexpfx.irctest.app.WifiList;
import br.com.alexpfx.irctest.app.mvp.model.interactor.Interactor;

import java.util.Date;

/**
 * Created by alex on 16/07/2015.
 */
public interface PostResultsUseCase extends Interactor {
    void execute(String id, String channel, WifiList list, Date eventTime);
}
