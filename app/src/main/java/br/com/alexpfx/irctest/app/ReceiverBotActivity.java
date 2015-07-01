package br.com.alexpfx.irctest.app;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceiverBotActivity extends AppCompatActivity implements IrcBotListener, ReceiverBotListener {

    @Bind(value = R.id.tvIrcServerStatus)
    TextView tvServerStatus;

    private ReceiverBot receiverBot;

    private WifiListener wifiListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_bot);
        receiverBot = new ReceiverBot("receiverBotName", "botLogin", "irc.freenode.org");
        receiverBot.setIrcBotListener(this);
        wifiListener = new WifiListener((WifiManager) getSystemService(WIFI_SERVICE), this);
        ButterKnife.bind(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_receiver_bot, menu);
        return true;
    }

    @Override
    protected void onResume() {
        wifiListener.addListener(receiverBot);
        wifiListener.registerReceiver();
        super.onResume();
    }

    @Override
    protected void onStop() {
        wifiListener.removeListener(receiverBot);
        wifiListener.unregisterReceiver();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onIrcBotConnect(IrcBot ircBot) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_green_600));
                tvServerStatus.setText("Connected");
            }
        });
    }

    @Override
    public void onIrcBotDisconnect(IrcBot ircBot) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_red_600));
                tvServerStatus.setText("Disconneced");
            }
        });

    }

    @Override
    public void onMatch(String ssid, int rssid) {

    }
}
