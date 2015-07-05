package br.com.alexpfx.irctest.app.mvp.model.interactor;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by alexandre on 05/07/15.
 */
public class MainThreadImpl implements MainThread {
    private Handler handler;

    public MainThreadImpl() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
