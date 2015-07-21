package br.com.alexpfx.irctest.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcChannelPresenter;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcChannelPresenterImpl;
import br.com.alexpfx.irctest.app.mvp.view.ChannelView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalkerBotActivity extends AppCompatActivity implements ChannelView {

    @Bind(value = R.id.tvIrcServerStatus)
    TextView tvServerStatus;

    @Bind(value = R.id.tvChannelStatus)
    TextView tvChannelStatus;

    @Bind(value = R.id.edtChannelName)
    EditText edtChannelName;

    private IrcChannelPresenter ircChannelPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_bot);
        ButterKnife.bind(this);

        ircChannelPresenter = new IrcChannelPresenterImpl(this);

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

    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
