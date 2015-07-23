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
import android.widget.TextView;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.UserIdentity;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl.IrcConnectUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl.IrcDisconnectUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl.PostResultsJsonImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.impl.RegisterAsListenerUseCaseImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.json.impl.GsonWifiInfoJsonConverterImpl;
import br.com.alexpfx.irctest.app.mvp.presenters.*;
import br.com.alexpfx.irctest.app.mvp.view.ChannelView;
import br.com.alexpfx.irctest.app.mvp.view.IrcConnectionView;
import br.com.alexpfx.irctest.app.mvp.view.IrcListenerView;
import br.com.alexpfx.irctest.app.mvp.view.SendMessageView;
import br.com.alexpfx.irctest.app.ottobus.BusProvider;
import br.com.alexpfx.irctest.app.ottobus.events.WifiReceived;
import br.com.alexpfx.irctest.app.receivers.WifiScanAlarmReceiver;
import br.com.alexpfx.irctest.app.receivers.WifiScanResultReceiver;
import br.com.alexpfx.irctest.app.utils.NetAddressUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.otto.Subscribe;
import org.apache.log4j.BasicConfigurator;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements IrcConnectionView, ChannelView, SendMessageView, IrcListenerView {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int DURATION = 60;
    @Bind(R.id.edtLogStatus)
    EditText edtLogStatus;
    @Bind(R.id.txtIpAddress)
    TextView txtIpAddress;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private WifiScanResultReceiver wifiScanResultBroadcastReceiver;
    private IrcConnectionPresenter ircConnectionPresenter;
    private SendMessagePresenter sendMessagePresenter;
    private String CHANNEL = "garbil";
    private IrcChannelPresenter ircChannelPresenter;
    private IrcListenerPresenter ircListenerPresenter;
    private String uniqueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        /* Log4j */
        BasicConfigurator.configure();

        BusProvider.INSTANCE.get().register(this);

        uniqueId = ((App) getApplication()).getUniqueId();

        Intent intent = new Intent(getApplicationContext(), WifiScanAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        wifiScanResultBroadcastReceiver = new WifiScanResultReceiver();

        ircConnectionPresenter = new IrcConnectionPresenterImpl(this, new IrcConnectUseCaseImpl(), new IrcDisconnectUseCaseImpl());
        ircChannelPresenter = new IrcChannelPresenterImpl(this);
        sendMessagePresenter = new SendMessagePresenterImpl(this, new PostResultsJsonImpl(new GsonWifiInfoJsonConverterImpl(typeParamterClass)));
        ircListenerPresenter = new IrcListenerPresenterImpl(this, new RegisterAsListenerUseCaseImpl());
        initializeApp();

    }

    @Override
    protected void onStart() {
        final String localIpAddress = NetAddressUtils.getLocalIpAddress();
        txtIpAddress.setText(localIpAddress);
        registerReceiver(wifiScanResultBroadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onStart();
    }

    void initializeApp() {
        setupScanAlarm();
        connectToServer();

    }

    private void registerListener() {
        ircListenerPresenter.register(uniqueId);
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
    public void showConnectionSuccess() {
        appendLog("connected to irc \n");
        joinChannel();
        registerListener();

    }

    @Override
    public void showConnectionError(String message) {
        appendLog(message);
        appendLog("\n");
    }

    @Override
    public void showDisconnectonSuccess() {
        appendLog("disconnected\n");
    }

    @Override
    public void showDisconnectionError(String message) {
        appendLog(message);
    }

    @Override
    public void showChannelJoined(String channel) {
        appendLog("joined: " + channel);
    }

    @Override
    public void showChannelJoinError(String message) {
        appendLog("join error: " + message);
    }

    @Subscribe
    public void onWifiReceived(WifiReceived wifiReceived) {

        sendMessagePresenter
                .sendWifiList(wifiReceived.getWifiList(), uniqueId, CHANNEL, new Date());
    }

    @Override
    public void showRegisterSuccess() {

    }
}
