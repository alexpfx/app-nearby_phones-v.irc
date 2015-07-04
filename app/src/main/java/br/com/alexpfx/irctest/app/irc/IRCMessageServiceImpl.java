package br.com.alexpfx.irctest.app.irc;

import com.ircclouds.irc.api.IRCApi;

/**
 * Created by alexandre on 02/07/15.
 */
public class IRCMessageServiceImpl implements IRCMessageService {
    private final String tag = IRCMessageServiceImpl.class.getSimpleName();

    //TODO: injetar
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

}
