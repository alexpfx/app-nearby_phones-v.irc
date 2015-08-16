package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.android.lib.network.irc.IrcChannelMessageListener;
import br.com.alexpfx.android.lib.network.irc.MessageFilter;
import br.com.alexpfx.android.lib.network.wifi.WifiListMessageFilterImpl;
import br.com.alexpfx.android.lib.network.irc.interactor.RegisterAsListenerUseCase;
import br.com.alexpfx.android.lib.network.wifi.SimpleWifiInfoBag;
import br.com.alexpfx.irctest.app.mvp.view.IrcListenerView;

/**
 * Created by alexandre on 22/07/15.
 */
public class IrcListenerPresenterImpl implements IrcListenerPresenter, RegisterAsListenerUseCase.Callback, MessageFilter.OnFilteredMessageListener {
    private IrcListenerView ircListenerView;
    private RegisterAsListenerUseCase registerAsListenerUseCase;
    private String appUid;

    public IrcListenerPresenterImpl(IrcListenerView ircListenerView, RegisterAsListenerUseCase registerAsListenerUseCase, String appUid) {
        this.ircListenerView = ircListenerView;
        this.registerAsListenerUseCase = registerAsListenerUseCase;
        this.appUid = appUid;
    }

    @Override
    public void register() {
        registerAsListenerUseCase.execute(this);
    }

    @Override
    public void unregister() {

    }

    @Override
    public void onRegisterSuccess(IrcChannelMessageListener listener) {
        ircListenerView.showRegisterSuccess();
        final WifiListMessageFilterImpl filter = new WifiListMessageFilterImpl(appUid);
        filter.setListener(this);
        listener.setMessageFilter(filter);
    }

    @Override
    public void onFilteredMessage(String channel, String user, String uniqueId, SimpleWifiInfoBag bag) {

    }
}
