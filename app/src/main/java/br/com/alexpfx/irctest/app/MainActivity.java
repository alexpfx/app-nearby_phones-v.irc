package br.com.alexpfx.irctest.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.UserIdentity;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcChannelPresenter;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcChannelPresenterImpl;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcConnectionPresenter;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcConnectionPresenterImpl;
import br.com.alexpfx.irctest.app.mvp.view.ChannelView;
import br.com.alexpfx.irctest.app.mvp.view.IrcConnectionView;
import br.com.alexpfx.irctest.app.receivers.WifiScanAlarmReceiver;
import br.com.alexpfx.irctest.app.receivers.WifiScanResultReceiver;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements IrcConnectionView, ChannelView {

    public static final int DURATION = 60;
    private static final String TAG = MainActivity.class.getSimpleName();
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private WifiScanResultReceiver wifiScanResultBroadcastReceiver;

    @Bind(R.id.edtLogStatus)
    EditText edtLogStatus;

    private IrcConnectionPresenter ircConnectionPresenter;
    private IrcChannelPresenter ircChannelPresenter;
    private String CHANNEL = "garbil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        /* Log4j */
        BasicConfigurator.configure();

        Intent intent = new Intent(getApplicationContext(), WifiScanAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        wifiScanResultBroadcastReceiver = new WifiScanResultReceiver();

        ircConnectionPresenter = new IrcConnectionPresenterImpl(this);
        ircChannelPresenter = new IrcChannelPresenterImpl(this);

        initializeApp();

    }

    @Override
    protected void onStart() {
        registerReceiver(wifiScanResultBroadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onStart();
    }

    void initializeApp() {
        setupScanAlarm();
        connectToServer();
    }

    private void joinChannel() {
        ircChannelPresenter.join(CHANNEL);
    }

    private void connectToServer() {
        final UserIdentity user = new UserIdentity.Builder().name("axnd").email("eays@com.com")
                                                            .nickname("bellairind")
                                                            .alternative("bellairx").build();
        ServerIdentity server = new ServerIdentity.Builder().ircServer("irc.freenode.org").build();
        ircConnectionPresenter.connect(user, server);

    }

    void setupScanAlarm() {
        final long interval = TimeUnit.SECONDS.toMillis(DURATION);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }

    @Override
    protected void onDestroy() {
        alarmManager.cancel(pendingIntent);
        unregisterReceiver(wifiScanResultBroadcastReceiver);
        ircConnectionPresenter.disconnect();
        super.onDestroy();
    }

    private void appendLog(String text) {
        edtLogStatus.getText().append(text);
    }

    @Override
    public void showConnectedToIrc() {
        appendLog("connected to irc \n");
        joinChannel();

    }

    @Override
    public void showConnectionError(String message) {
        appendLog(message);
        appendLog("\n");
    }

    @Override
    public void showDisconnected() {
        appendLog("disconnected\n");
    }

    @Override
    public void showNotConnected() {
        appendLog("not connected\n");
    }

    @Override
    public void showChannelJoined(String channel) {
        appendLog("joined: " + channel);
    }

    @Override
    public void showChannelJoinError(String message) {
        appendLog("join error: " + message);
    }
}
