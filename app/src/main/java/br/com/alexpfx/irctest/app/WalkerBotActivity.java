package br.com.alexpfx.irctest.app;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import br.com.alexpfx.irctest.app.irc.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalkerBotActivity extends AppCompatActivity implements IrcBotListener, AttemptCallback <IRCStateHolder>{

    private IRCService ircService = new IRCServiceImpl();

    @Bind(value = R.id.tvIrcServerStatus)
    TextView tvServerStatus;

    private static WalkerBot walkerBot;

    private WifiListener wifiListener;
    private String tag = WalkerBotActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_bot);
        if (walkerBot == null) {
            walkerBot = WalkerBot.getInstance("walkerBotWalker", "walkerLogin", "irc.freenode.org");
        }
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
        walkerBot.joinChannel("#libgdx");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvServerStatus.setText("Connected");
                tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_green_600));
            }
        });
    }

    @Override
    public void onIrcBotDisconnect(IrcBot ircBot) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvServerStatus.setText("Disconnect");
                tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_red_600));
            }
        });
    }

    @OnClick(R.id.btnConnect)
    public void btnConnectClick() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                IRCConnParameters p = new IRCConnParameters.Builder ("irc.freenode.org", "alexpfx", "alexpfbh").build();
                ircService.connect(p, WalkerBotActivity.this);

                return null;
            }
        }.execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onConnect(IRCStateHolder ircState) {
        System.out.println(ircState);

    }

    @Override
    public void onSuccess(IRCStateHolder ircState) {
        ircService.join("#libgdx", new AttemptCallback.LOG_ONLY_CALLBACK());
    }

    public void onFailure(Exception exception) {
        exception.printStackTrace();

    }
}
