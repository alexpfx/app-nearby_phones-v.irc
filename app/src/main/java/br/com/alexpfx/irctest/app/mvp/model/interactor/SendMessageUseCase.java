package br.com.alexpfx.irctest.app.mvp.model.interactor;

import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.Executor;

/**
 * Created by alex on 11/07/2015.
 */
public interface SendMessageUseCase extends Interactor {

    void execute (String user, String message);



}
