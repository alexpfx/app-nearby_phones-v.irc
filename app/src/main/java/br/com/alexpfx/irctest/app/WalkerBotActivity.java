package br.com.alexpfx.irctest.app;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import br.com.alexpfx.irctest.app.irc.*;
import br.com.alexpfx.irctest.app.mvp.view.IrcConnectionView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalkerBotActivity extends AppCompatActivity implements IrcConnectionView {

    @Bind(value = R.id.tvIrcServerStatus)
    TextView tvServerStatus;

    private IRCConnectionService ircConnectionService = new IRCConnectionServiceImpl();
    private IRCChannelService ircChannelService = new IRCChannelServiceImpl();
    private IRCMessageService ircMessageService = new IRCMessageServiceImpl();
    private WifiListener wifiListener;
    private String tag = WalkerBotActivity.class.getSimpleName();
    private Handler handler = new Handler();
    private boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_bot);
        wifiListener = new WifiListener((WifiManager) getSystemService(Context.WIFI_SERVICE), this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
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



    @OnClick(R.id.btnConnect)
    public void btnConnectClick() {

    }

    @Override
    public void showConnectedToIrc() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvServerStatus.setText("Connected");
                tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_green_600));
            }
        });
    }

    @Override
    public void showDisconnectedFromIrc() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvServerStatus.setText("Disconnect");
                tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_red_600));
            }
        });

    }
}
