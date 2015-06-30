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

import static br.com.alexpfx.irctest.app.TextLogUtils.concat;

public class MainActivity extends ActionBarActivity implements IrcBotListener, ReceiverBotListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String IRC_SERVER = "irc.freenode.org";
    private static final String CHANNEL = "#garbil";

    @Bind(R.id.tvCountPvt)
    TextView tvCount;

    @Bind(R.id.tvMsgs)
    TextView tvMsg;

    private ReceiverBot receiverBot;
    private WalkerBot walkerBot;
    private WifiListener wifiListener;
    private BotStarter listenerStarter;
    private BotStarter walkerStarter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        walkerBot = new WalkerBot(this);
        walkerStarter = new BotStarter(walkerBot);

        receiverBot = new ReceiverBot();
        receiverBot.setIrcBotListener(this);
        receiverBot.setReceiverBotListener(this);

        walkerBot.addWalkerListener(receiverBot.getUserIdentity());

        listenerStarter = new BotStarter(receiverBot);

        ButterKnife.bind(this);
        wifiListener = new WifiListener((WifiManager) getSystemService(WIFI_SERVICE), this);
        wifiListener.scan();

        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        wifiListener.registerReceiver();
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
    public void onIrcBotConnect(IrcBot ircBot) {
        Log.i(TAG, concat(" ", "connected"));
        ircBot.joinChannel(CHANNEL);
        wifiListener.addListener((WifiListener.WifiNetworkInfoReceiveListener) ircBot);
    }

    @Override
    public void onIrcBotDisconnect(IrcBot ircBot) {
        Log.i(TAG, concat(" ", "disconnected", "try to reconnect"));
        new BotStarter(ircBot).connect(IRC_SERVER);
        wifiListener.removeListener((WifiListener.WifiNetworkInfoReceiveListener) ircBot);

    }

    @OnClick(R.id.btnListener)
    void onListenerClick() {
        listenerStarter.connect(IRC_SERVER);
    }

    @OnClick(R.id.btnWalker)
    void onWalkerClick() {
        walkerStarter.connect(IRC_SERVER);
    }

    @Override
    public void onMatch(String ssid, int rssid) {
        tvMsg.setText(tvMsg.getText() + " " + ssid);
        tvCount.setText(String.valueOf(rssid));
    }
}
