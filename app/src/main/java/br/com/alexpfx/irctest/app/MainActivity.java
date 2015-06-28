package br.com.alexpfx.irctest.app;

import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import org.jibble.pircbot.IrcException;

import java.io.IOException;


public class MainActivity extends ActionBarActivity implements WifiListener.WifiNetworkInfoReceiveListener {

    MyBot bot;
    private WifiListener wifiListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiListener = new WifiListener((WifiManager) getSystemService(WIFI_SERVICE), this, this);

        bot = new MyBot();

        AsyncTask<Void, Void, Void> a = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                connect();
                return null;
            }
        };

        a.execute();


    }

    @Override
    protected void onResume() {
        wifiListener.registerReceiver();
        wifiListener.scan();
        super.onResume();
    }

    @Override
    protected void onPause() {
        wifiListener.unregisterReceiver();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void connect() {
        try {
            bot.connect("irc.brasirc.org");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void receive(String bssid, String ssid) {
        bot.addToPool(ssid, bssid);
    }
}
