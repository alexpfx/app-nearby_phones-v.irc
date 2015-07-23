package br.com.alexpfx.irctest.app.mvp.presenters;

import android.util.Log;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.IrcChannelMessageListener;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.MessageFilter;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.WifiListMessageFilterImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.RegisterAsListenerUseCase;
import br.com.alexpfx.irctest.app.mvp.model.domain.wifi.SimpleWifiInfoBag;
import br.com.alexpfx.irctest.app.mvp.view.IrcListenerView;
import br.com.alexpfx.irctest.app.utils.TagUtils;

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
    public void onFilteredMessage(String channel, String user, String originalMessage, SimpleWifiInfoBag bag) {
        Log.d(TagUtils.tag(), bag.toString());
    }
}
