package br.com.alexpfx.irctest.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import br.com.alexpfx.android.lib.base.provider.BusProvider;
import br.com.alexpfx.android.lib.network.irc.ServerIdentity;
import br.com.alexpfx.android.lib.network.irc.UserIdentity;
import br.com.alexpfx.android.lib.network.irc.interactor.IrcConnectUseCaseImpl;
import br.com.alexpfx.android.lib.network.irc.interactor.IrcDisconnectUseCaseImpl;
import br.com.alexpfx.android.lib.network.irc.interactor.PostResultsJsonImpl;
import br.com.alexpfx.android.lib.network.irc.interactor.RegisterAsListenerUseCaseImpl;
import br.com.alexpfx.android.lib.network.wifi.GsonWifiInfoJsonConverterImpl;
import br.com.alexpfx.android.lib.network.wifi.SimpleWifiInfoBag;
import br.com.alexpfx.android.lib.network.wifi.WifiInfoBag;
import br.com.alexpfx.android.lib.network.wifi.WifiReceived;
import br.com.alexpfx.android.lib.network.wifi.WifiRepository;
import br.com.alexpfx.android.lib.network.wifi.WifiScanAlarmReceiver;
import br.com.alexpfx.android.lib.network.wifi.WifiScanResultReceiver;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcChannelPresenter;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcChannelPresenterImpl;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcConnectionPresenter;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcConnectionPresenterImpl;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcListenerPresenter;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcListenerPresenterImpl;
import br.com.alexpfx.irctest.app.mvp.presenters.SendMessagePresenter;
import br.com.alexpfx.irctest.app.mvp.presenters.SendMessagePresenterImpl;
import br.com.alexpfx.irctest.app.mvp.view.ChannelView;
import br.com.alexpfx.irctest.app.mvp.view.IrcConnectionView;
import br.com.alexpfx.irctest.app.mvp.view.IrcListenerView;
import br.com.alexpfx.irctest.app.mvp.view.SendMessageView;
import br.com.alexpfx.irctest.app.utils.NetAddressUtils;
import butterknife.ButterKnife;

import static br.com.alexpfx.irctest.app.utils.TagUtils.method;
import static br.com.alexpfx.irctest.app.utils.TagUtils.tag;

public class IrcActivity extends AppCompatActivity implements IrcConnectionView, ChannelView, SendMessageView, IrcListenerView {
    private static final int SCAN_ALARM_WAKE_UP_INTERVAL = 60;

    //    @Bind(R.id.edtLogStatus)
    EditText edtLogStatus;
    //    @Bind(R.id.txtIpAddress)
    TextView txtIpAddress;

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private WifiScanResultReceiver wifiScanResultReceiver;
    private IrcConnectionPresenter ircConnectionPresenter;
    private SendMessagePresenter sendMessagePresenter;
    private String CHANNEL = "garbil";
    private IrcChannelPresenter ircChannelPresenter;
    private IrcListenerPresenter ircListenerPresenter;
    private String uniqueId;
    private WifiRepository wifiRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irc);

        uniqueId = ((App) getApplication()).getUniqueId();
        setupThirdPartyTools();
        initPresenters();

        setupWifiScan();
        connectToServer();

    }

    private void setupThirdPartyTools() {
        ButterKnife.bind(this);
        BusProvider.getInstance().register(this);
        /* Log4j */
//       BasicConfigurator.configure();
    }

    private void initPresenters() {
        ircConnectionPresenter = new IrcConnectionPresenterImpl(this, new IrcConnectUseCaseImpl(), new IrcDisconnectUseCaseImpl());
        ircChannelPresenter = new IrcChannelPresenterImpl(this);
        sendMessagePresenter = new SendMessagePresenterImpl(new PostResultsJsonImpl(new GsonWifiInfoJsonConverterImpl()));
        ircListenerPresenter = new IrcListenerPresenterImpl(this, new RegisterAsListenerUseCaseImpl(), uniqueId);
    }

    private void registerListener() {
        ircListenerPresenter.register();
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

    void setupWifiScan() {
        wifiRepository = new WifiRepository();
        setupScanAlarm();

        wifiScanResultReceiver = new WifiScanResultReceiver();
    }

    private void setupScanAlarm() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), WifiScanAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
        long interval = TimeUnit.SECONDS.toMillis(SCAN_ALARM_WAKE_UP_INTERVAL);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }

    private void appendLog(String text) {
        edtLogStatus.getText().append(text).append("\n");
    }

    @Override
    public void showConnectionSuccess() {
        appendLog("connected to irc");
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
        appendLog("disconnected");
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
        final WifiInfoBag wifiInfoBag = wifiReceived.getWifiInfoBag();
        sendMessagePresenter
                .sendWifiList(wifiInfoBag, uniqueId, CHANNEL, new Date());
        wifiRepository.addAll(wifiInfoBag);
        Log.d(tag(), method("repository size: " + wifiRepository.getSize()));
    }

    @Override
    public void showRegisterSuccess() {
        appendLog("registered");

    }

    @Override
    public void showWifiReceivedFromIrc(String channel, String user, SimpleWifiInfoBag wifiBag) {

    }

    @Override
    protected void onStart() {
        final String localIpAddress = NetAddressUtils.getLocalIpAddress();
        txtIpAddress.setText(localIpAddress);
        registerReceiver(wifiScanResultReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        alarmManager.cancel(pendingIntent);
        unregisterReceiver(wifiScanResultReceiver);
        ircConnectionPresenter.disconnect();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_irc, menu);
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
}
