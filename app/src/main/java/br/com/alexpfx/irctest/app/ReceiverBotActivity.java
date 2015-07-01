package br.com.alexpfx.irctest.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceiverBotActivity extends ActionBarActivity implements IrcBotListener, ReceiverBotListener {

    @Bind(value = R.id.tvIrcServerStatus)
    TextView tvServerStatus;

    private ReceiverBot receiverBot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_bot);
        receiverBot = new ReceiverBot("receiverBotName", "botLogin", "irc.freenode.org");
        receiverBot.setIrcBotListener(this);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_receiver_bot, menu);
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
        tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_green_600));
        tvServerStatus.setText("Connected");
    }

    @Override
    public void onIrcBotDisconnect(IrcBot ircBot) {
        tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_red_600));
        tvServerStatus.setText("Disconneced");

    }

    @Override
    public void onMatch(String ssid, int rssid) {

    }
}
