package br.com.alexpfx.irctest.app.irc;

import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.domain.messages.ChannelPrivMsg;
import com.ircclouds.irc.api.domain.messages.UserPrivMsg;
import com.ircclouds.irc.api.domain.messages.interfaces.IMessage;
import com.ircclouds.irc.api.listeners.IMessageListener;

/**
 * Created by alexandre on 02/07/15.
 */
public class IRCMessageServiceImpl implements IRCMessageService {

    private final String tag = IRCMessageServiceImpl.class.getSimpleName();
    private IRCApi api = IRCApiSingleton.INSTANCE.get();
    private MessageListenerWrapper messageListenerWrapper = new MessageListenerWrapper();

    public IRCMessageServiceImpl() {
        api.addListener(messageListenerWrapper);
    }

    @Override
    public void registerListener(MessageListener m) {
        messageListenerWrapper.addMessageListener(m);
    }

    @Override
    public void sendMessage(String target, String message) {
        api.message(target, message);
    }

    //TODO: mover
    private class MessageListenerWrapper implements IMessageListener {
        private MessageListeners listeners = new MessageListeners();

        public void addMessageListener(MessageListener messageListener) {
            listeners.add(messageListener);
        }

        @Override
        public void onMessage(IMessage aMessage) {
            deliveryMessage(aMessage);
        }

        private void deliveryMessage(IMessage message) {
            if (message instanceof UserPrivMsg) {
                notifyAll((UserPrivMsg) message);
            } else if (message instanceof ChannelPrivMsg) {
                notifyAll((ChannelPrivMsg) message);
            }
        }

        private void notifyAll(ChannelPrivMsg message) {
            listeners.notifyChannelMessage(message.getChannelName(), message.getSource().getNick(), message.getText());
        }

        private void notifyAll(UserPrivMsg message) {
            listeners.notifyPrivateMessage(message.getSource().getNick(), message.getText());
        }

    }

}
