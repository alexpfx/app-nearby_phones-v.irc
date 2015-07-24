package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

/**
 * Created by alexandre on 23/07/15.
 */
public interface TimedOut<T> {
    boolean isExpired();

}
