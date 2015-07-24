package br.com.alexpfx.irctest.app.mvp.model.domain.wifi;

import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;

/**
 * Created by alexandre on 23/07/15.
 */
public class TimedOutObject<T> implements TimedOut<T> {
    private long expiresTime;
    private T object;

    public TimedOutObject(int timeout, T object) {
        postbone(timeout);
        this.object = object;
    }

    @Override
    public boolean isExpired() {
        return currentTimeMillis() > expiresTime;
    }

    private void postbone(int seconds) {
        synchronized (this) {
            expiresTime = currentTimeMillis() + TimeUnit.SECONDS.toMillis(seconds);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimedOutObject<?> that = (TimedOutObject<?>) o;

        return object.equals(that.object);

    }

    @Override
    public int hashCode() {
        return object.hashCode();
    }
}
