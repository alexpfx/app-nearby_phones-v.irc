package br.com.alexpfx.irctest.app;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.alexpfx.irctest.app.TextStringUtils.logConcat;

public class MainActivity extends ActionBarActivity implements WifiListener.WifiNetworkInfoReceiveListener, IrcBotListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String IRC_SERVER = "irc.freenode.org";
    private static final String CHANNEL = "#msggroupexample";

    private WifiListener wifiListener;

    @Bind(R.id.tvCountPvt)
    TextView tvCount;

    @Bind(R.id.tvMsgs)
    TextView tvMsg;

    private ListenerBot listenerBot;
    private WalkerBot walkerBot;

    private BotStarter listenerStarter;
    private BotStarter walkerStarter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiListener = new WifiListener((WifiManager) getSystemService(WIFI_SERVICE), this, this);

        walkerBot = new WalkerBot(this);
        walkerStarter = new BotStarter(walkerBot);

        listenerBot = new ListenerBot(this);
        listenerStarter = new BotStarter(listenerBot);

        ButterKnife.bind(this);

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

    @Override
    public void receive(String bssid, String ssid) {
    }

    @Override
    public void onIrcBotConnect(String tag) {
        Log.i(TAG, logConcat(" ", tag, "connected"));
    }

    @Override
    public void onIrcBotDisconnect(String tag) {
        Log.i(TAG, logConcat(" ", tag, "disconnected", "try to reconnect"));
        listenerStarter.connect(IRC_SERVER);
    }

    @OnClick(R.id.btnListener)
    void onListenerClick() {
        listenerStarter.connect(IRC_SERVER);
    }

    @OnClick(R.id.btnWalker)
    void onWalkerClick() {
        walkerStarter.connect(IRC_SERVER);
    }

}
