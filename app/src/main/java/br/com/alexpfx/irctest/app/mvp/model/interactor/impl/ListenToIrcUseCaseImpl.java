package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import br.com.alexpfx.irctest.app.mvp.model.irc.IRCApiSingleton;
import br.com.alexpfx.irctest.app.mvp.model.interactor.ListenToIrcUseCase;
import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.domain.messages.ChanPartMessage;
import com.ircclouds.irc.api.domain.messages.UserPrivMsg;
import com.ircclouds.irc.api.listeners.VariousMessageListenerAdapter;

/**
 * Created by alexandre on 07/07/15.
 */
public class ListenToIrcUseCaseImpl implements ListenToIrcUseCase {
    private IRCApi ircApi = IRCApiSingleton.INSTANCE.get();


    @Override
    public void registerListener(final String filterString, Callback callback) {
        MessageFilter filter = new MessageFilter() {
            @Override
            public boolean catchMessage(String searchString) {
                return searchString.contains(filterString);
            }

        };
        ircApi.addListener(new MessageListener(filter, callback));

    }

    interface MessageFilter {
        boolean catchMessage(String searchString);
    }

    class MessageListener extends VariousMessageListenerAdapter {
        private MessageFilter filter;
        private Callback callback;

        public MessageListener(MessageFilter filter, Callback callback) {
            this.filter = filter;
            this.callback = callback;
        }


        @Override
        public void onUserPrivMessage(UserPrivMsg aMsg) {
            if (!filter.catchMessage(aMsg.getText())) {
                return;
            }
            callback.onPrivateMessage(aMsg.getSource().getNick(), aMsg.getText());
        }

        @Override
        public void onChannelPart(ChanPartMessage aMsg) {
            super.onChannelPart(aMsg);
        }
    }

}
