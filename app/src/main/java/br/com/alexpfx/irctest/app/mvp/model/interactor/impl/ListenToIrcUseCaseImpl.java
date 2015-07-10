package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import br.com.alexpfx.irctest.app.irc.IRCApiSingleton;
import br.com.alexpfx.irctest.app.mvp.model.interactor.ListenToIrcUseCase;
import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.domain.messages.UserPrivMsg;
import com.ircclouds.irc.api.listeners.VariousMessageListenerAdapter;

/**
 * Created by alexandre on 07/07/15.
 */
public class ListenToIrcUseCaseImpl implements ListenToIrcUseCase {
    private IRCApi ircApi = IRCApiSingleton.INSTANCE.get();

    public void register() {
        ircApi.addListener(new MessageListener(null));
    }

    public void unregister() {
        ircApi.deleteListener(new MessageListener(null));
    }

    @Override
    public void run() {

    }

    @Override
    public void registerListener(String filter, Callback callback) {

    }

    class MessageListener extends VariousMessageListenerAdapter {
        private MessageFilter filter;

        public MessageListener(MessageFilter filter) {
            this.filter = filter;
        }

        @Override
        public void onUserPrivMessage(UserPrivMsg aMsg) {
            if (!filter.catchMessage(aMsg.getText())) {
                return;
            }

        }
    }

    interface MessageFilter {
        boolean catchMessage(String searchString);
    }

}
