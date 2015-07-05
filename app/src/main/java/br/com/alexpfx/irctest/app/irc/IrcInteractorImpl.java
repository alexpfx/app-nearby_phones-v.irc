package br.com.alexpfx.irctest.app.irc;

import com.ircclouds.irc.api.Callback;
import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.IServerParameters;
import com.ircclouds.irc.api.domain.IRCChannel;
import com.ircclouds.irc.api.domain.messages.ChannelPrivMsg;
import com.ircclouds.irc.api.domain.messages.UserPrivMsg;
import com.ircclouds.irc.api.listeners.VariousMessageListenerAdapter;
import com.ircclouds.irc.api.state.IIRCState;

/**
 * Created by alexandre on 04/07/15.
 */
public class IrcInteractorImpl implements IrcInteractor {
    //inject
    private IRCApi api;

    @Override
    public void join(String channel, final AttemptCallback<ChannelObject> callback) {
        api.joinChannel(channel, new Callback<IRCChannel>() {
            @Override
            public void onSuccess(IRCChannel aObject) {
                callback.onSuccess(new ChannelObject(aObject));
            }

            @Override
            public void onFailure(Exception aExc) {
                callback.onFailure(aExc);
            }
        });
    }

    @Override
    public void leave(String channel) {
        api.leaveChannel(channel);
    }

    @Override
    public void connect(IRCConnParameters ircConnParameters, final AttemptCallback<IRCStateHolder> callback) {
        final IServerParameters parameters = IRCServiceUtils
                .getServerParameters(ircConnParameters.getHostname(), ircConnParameters.getIdent(), ircConnParameters
                        .getLogin(), ircConnParameters.getNickName(), ircConnParameters.getAlternativeNickName());

        api.connect(parameters, new Callback<IIRCState>() {
            @Override
            public void onSuccess(IIRCState state) {
                callback.onSuccess(new IRCStateHolder(state));
            }

            @Override
            public void onFailure(Exception ex) {
                callback.onFailure(ex);
            }
        });
    }

    @Override
    public void disconnect() {
        api.disconnect();
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void sendMessage(String target, String message) {
        api.message(target, message);
    }

    @Override
    public void registerListener(final MessageListener m) {
        api.addListener(new VariousMessageListenerAdapter() {
            @Override
            public void onUserPrivMessage(UserPrivMsg aMsg) {
                m.onPrivateMessage(aMsg.getSource().getNick(), aMsg.getText());
            }

            @Override
            public void onChannelMessage(ChannelPrivMsg aMsg) {
                m.onMessage(aMsg.getChannelName(), aMsg.getSource().getNick(), aMsg.getText());
            }
        });
    }
}
