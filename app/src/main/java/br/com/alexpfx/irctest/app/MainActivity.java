package br.com.alexpfx.irctest.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private BroadcastReceiver wifiBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        BasicConfigurator.configure();

        Intent intent = new Intent(getApplicationContext(), WifiScanScheduleBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        wifiBroadcastReceiver = new WifiScanResultBroadcastReceiver();
    }

    @OnClick(R.id.btnOpenReceiverActivity)
    void onOpenReceiverClick() {
        startActivity(new Intent(MainActivity.this, ReceiverBotActivity.class));
    }

    @OnClick(R.id.btnOpenWalkerActivity)
    void onOpenWalkerClick() {
        startActivity(new Intent(MainActivity.this, WalkerBotActivity.class));
    }

    @OnClick(R.id.btnStartWifiAlarm)
    void onStartWifiAlarmClick() {
        final long interval = TimeUnit.SECONDS.toMillis(60);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }

    @OnClick(R.id.btnCancelWifiAlarm)
    void onCancelWifiAlarmClick() {
        alarmManager.cancel(pendingIntent);
    }

    @Override
    protected void onResume() {
        registerReceiver(wifiBroadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(wifiBroadcastReceiver);
        super.onStop();
    }
}
