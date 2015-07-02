package br.com.alexpfx.irctest.app.irc;

import android.util.Log;

/**
 * Created by alexandre on 02/07/15.
 */
public interface AttemptCallback<T> {
    void onSuccess(T ircState);

    void onFailure(Exception exception);

    public static final class LOG_ONLY_CALLBACK<T> implements AttemptCallback<T> {
        private final String tag = this.getClass().getSimpleName();

        @Override
        public void onSuccess(T ircState) {
            Log.i(tag, ircState.toString());

        }

        @Override
        public void onFailure(Exception exception) {
            Log.e(tag, exception.getMessage());
        }
    }
}
