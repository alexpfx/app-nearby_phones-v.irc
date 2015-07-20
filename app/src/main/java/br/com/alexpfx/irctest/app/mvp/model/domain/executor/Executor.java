package br.com.alexpfx.irctest.app.mvp.model.domain.executor;

import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.Interactor;

/**
 * Created by alexandre on 05/07/15.
 */
public interface Executor {
    void run(Interactor interactor);
}
