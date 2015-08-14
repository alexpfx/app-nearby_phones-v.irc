package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases;

import br.com.alexpfx.android.lib.base.mvpbase.executor.Interactor;

/**
 * Created by alex on 11/07/2015.
 */
public interface SendMessageUseCase extends Interactor {

    void execute(String user, String message);

}
