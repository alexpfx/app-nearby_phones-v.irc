package br.com.alexpfx.irctest.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String IRC_SERVER = "irc.freenode.org";
    private static final String CHANNEL = "#garbil";
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        BasicConfigurator.configure();
        Intent intent = new Intent(getApplicationContext(), PerformWifiScanBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Log.i(TAG, "onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        final long interval = TimeUnit.SECONDS.toMillis(5);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }

    @OnClick(R.id.btnCancelWifiAlarm)
    void onCancelWifiAlarmClick() {
        alarmManager.cancel(pendingIntent);
    }

}
