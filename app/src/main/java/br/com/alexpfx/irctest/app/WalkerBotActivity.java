package br.com.alexpfx.irctest.app;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class WalkerBotActivity extends AppCompatActivity implements IrcBotListener {

    @Bind(value = R.id.tvIrcServerStatus)
    TextView tvServerStatus;

    private WalkerBot walkerBot;

    private WifiListener wifiListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_bot);

        walkerBot = new WalkerBot("walkerBotWalker", "walkerLogin", "irc.freenode.org");
        walkerBot.setIrcBotListener(this);

        wifiListener = new WifiListener((WifiManager) getSystemService(Context.WIFI_SERVICE), this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        wifiListener.registerReceiver();
        wifiListener.addListener(walkerBot);
        super.onResume();
    }

    @Override
    protected void onPause() {
        wifiListener.unregisterReceiver();
        wifiListener.removeListener(walkerBot);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_walker_bot, menu);
        return true;
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
        tvServerStatus.setText("Connected");
        tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_green_600));
    }

    @Override
    public void onIrcBotDisconnect(IrcBot ircBot) {
        tvServerStatus.setText("Disconnect");
        tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_red_600));

    }
}
