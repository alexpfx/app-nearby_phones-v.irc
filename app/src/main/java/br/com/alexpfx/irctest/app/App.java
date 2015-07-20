package br.com.alexpfx.irctest.app;

import android.app.Application;

/**
 * Created by alexandre on 06/07/15.
 */
public class App extends Application {

    private String uniqueId;

    @Override
    public void onCreate() {
        super.onCreate();
        uniqueId = new AppIdentity().getUid(this.getApplicationContext());
    }

    public String getUniqueId() {
        return uniqueId;
    }
}
