package br.com.alexpfx.irctest.app.mvp.presenters;

import android.util.Log;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.IrcChannelMessageListener;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.MessageFilter;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.WifiListMessageFilterImpl;
import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.RegisterAsListenerUseCase;
import br.com.alexpfx.irctest.app.mvp.view.IrcListenerView;

import static br.com.alexpfx.irctest.app.utils.TagUtils.method;
import static br.com.alexpfx.irctest.app.utils.TagUtils.tag;

/**
 * Created by alexandre on 22/07/15.
 */
public class IrcListenerPresenterImpl implements IrcListenerPresenter, RegisterAsListenerUseCase.Callback, MessageFilter.OnFilteredMessageListener {
    private IrcListenerView ircListenerView;
    private RegisterAsListenerUseCase registerAsListenerUseCase;

    public IrcListenerPresenterImpl(IrcListenerView ircListenerView, RegisterAsListenerUseCase registerAsListenerUseCase) {
        this.ircListenerView = ircListenerView;
        this.registerAsListenerUseCase = registerAsListenerUseCase;
    }

    @Override
    public void register(String appUid) {
        registerAsListenerUseCase.execute(this);
    }

    @Override
    public void unregister() {

    }

    @Override
    public void onRegisterSuccess(IrcChannelMessageListener listener) {
        final WifiListMessageFilterImpl filter = new WifiListMessageFilterImpl();
        filter.setListener(this);
        listener.setMessageFilter(filter);
    }

    @Override
    public void onFilteredMessage(String channel, String user, String message) {
        Log.d(tag(), method(user));
    }
}
