package br.com.alexpfx.irctest.app;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReceiverBotActivity extends AppCompatActivity implements IrcBotListener, ReceiverBotListener {

    @Bind(value = R.id.tvIrcServerStatus)
    TextView tvServerStatus;

    private ReceiverBot receiverBot;

    private WifiScanBroadcastReceiver wifiScanBroadcastReceiver;
    private String tag = ReceiverBotActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_bot);
        receiverBot = new ReceiverBot("receiverBotName", "botLogin", "irc.freenode.org");
        receiverBot.setIrcBotListener(this);
        wifiScanBroadcastReceiver = new WifiScanBroadcastReceiver();
        ButterKnife.bind(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_receiver_bot, menu);
        return true;
    }

    @Override
    protected void onResume() {
        registerReceiver(wifiScanBroadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(wifiScanBroadcastReceiver);
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

    @OnClick(R.id.btnConnect)
    public void btnConnectClick() {

        Log.i(tag, "try connect");
        if (receiverBot.isConnected()) {
            receiverBot.disconnect();
        } else {
            receiverBot.connect();
        }
    }

}
