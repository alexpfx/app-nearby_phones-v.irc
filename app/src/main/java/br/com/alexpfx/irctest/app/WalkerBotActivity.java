package br.com.alexpfx.irctest.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import br.com.alexpfx.irctest.app.irc.*;
import br.com.alexpfx.irctest.app.mvp.model.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentify;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcConnectionPresenter;
import br.com.alexpfx.irctest.app.mvp.presenters.IrcConnectionPresenterImpl;
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

    private IrcConnectionPresenter ircConnectionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_bot);
        ButterKnife.bind(this);

        ircConnectionPresenter = new IrcConnectionPresenterImpl(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
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
        final UserIdentify user = new UserIdentify.Builder().name("alexandre").email("alexinternet@gmail.com")
                                                            .nickname("alexpfx")
                                                            .alternative("alexpfx2").build();
        ServerIdentity server = new ServerIdentity.Builder().ircServer("irc.freenode.org").build();
        ircConnectionPresenter.connect(user, server);

    }

    @Override
    public void showConnectedToIrc() {
        tvServerStatus.setText("Connected");
        tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_green_600));
    }

    @Override
    public void showDisconnectedFromIrc() {
        tvServerStatus.setText("Disconnect");
        tvServerStatus.setBackgroundColor(getResources().getColor(R.color.md_red_600));

    }

    @Override
    public void showConnectionError(String message) {
        Toast.makeText(this, "Erro ao conectar ao servidor de irc", Toast.LENGTH_SHORT).show();
    }
}
