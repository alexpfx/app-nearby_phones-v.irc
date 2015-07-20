package br.com.alexpfx.irctest.app.mvp.model.domain.executor;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by alexandre on 05/07/15.
 */
public class MainThreadImpl implements MainThread {
    private Handler handler;

    private MainThreadImpl() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }

    public enum MainThreadSingleton {
        INSTANCE;

        private MainThread instance = new MainThreadImpl();

        public MainThread get() {
            return instance;
        }

    }
}
