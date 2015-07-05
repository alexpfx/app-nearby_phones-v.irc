package br.com.alexpfx.irctest.app.mvp.model.interactor.executor;

/**
 * Created by alexandre on 05/07/15.
 */
public interface MainThread {

    void post(final Runnable runnable);
}
