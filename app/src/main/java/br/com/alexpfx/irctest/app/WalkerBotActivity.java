package br.com.alexpfx.irctest.app;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.alexpfx.irctest.app.receivers.WifiScanResultBroadcastReceiver;
import br.com.alexpfx.irctest.app.mvp.model.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentity;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcChannelPresenter;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcChannelPresenterImpl;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcConnectionPresenter;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcConnectionPresenterImpl;
import br.com.alexpfx.irctest.app.mvp.view.ChannelView;
import br.com.alexpfx.irctest.app.mvp.view.IrcConnectionView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalkerBotActivity extends AppCompatActivity implements IrcConnectionView, ChannelView {

    @Bind(value = R.id.tvIrcServerStatus)
    TextView tvServerStatus;

    @Bind(value = R.id.tvChannelStatus)
    TextView tvChannelStatus;

    @Bind(value = R.id.edtChannelName)
    EditText edtChannelName;

    private IrcConnectionPresenter ircConnectionPresenter;
    private IrcChannelPresenter ircChannelPresenter;
    private WifiScanResultBroadcastReceiver wifiScanResultBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_bot);
        ButterKnife.bind(this);

        ircConnectionPresenter = new IrcConnectionPresenterImpl(this);
        ircChannelPresenter = new IrcChannelPresenterImpl(this);

        wifiScanResultBroadcastReceiver = new WifiScanResultBroadcastReceiver();

    }


    @Override
    protected void onResume() {
        registerReceiver(wifiScanResultBroadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }


    @Override
    protected void onPause() {
        unregisterReceiver(wifiScanResultBroadcastReceiver);
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
        toast("Connection");
        final UserIdentity user = new UserIdentity.Builder().name("axnd").email("eays@com.com")
                .nickname("bellairind")
                .alternative("bellair").build();
        ServerIdentity server = new ServerIdentity.Builder().ircServer("irc.freenode.org").build();
        ircConnectionPresenter.connect(user, server);

    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnDisconnect)
    public void btnDisconnect() {
        toast("Disconnecting");
        ircConnectionPresenter.disconnect();

    }

    @OnClick(R.id.btnJoinChannel)
    public void btnJoinChannel() {
        toast("Joining");
        final Editable text = edtChannelName.getText();
        if (!text.equals("")) {
            ircChannelPresenter.join(text.toString());
        }
    }

    @Override
    public void showConnectedToIrc() {
        tvServerStatus.setText("Connected");
        tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_green_600));
        toast("Connected");
    }

    @Override
    public void showConnectionError(String message) {
        Toast.makeText(this, "Erro ao conectar ao servidor de irc", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDisconnected() {
        tvServerStatus.setText("Disconnected");
        tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_red_600));
        toast("Disconnected");
    }

    @Override
    public void showNotConnected() {
        toast("Not Connected");
    }

    @Override
    public void showChannelJoined(String info) {
        tvChannelStatus.setText("joined");
        toast(info);
        tvChannelStatus.setBackgroundColor(getResources().getColor(R.color.md_green_600));
    }

    @Override
    public void showChannelJoinError(String message) {
        tvChannelStatus.setText(message);
        tvChannelStatus.setBackgroundColor(getResources().getColor(R.color.md_red_600));
    }
}
