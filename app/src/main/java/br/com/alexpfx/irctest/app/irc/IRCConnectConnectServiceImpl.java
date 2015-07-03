package br.com.alexpfx.irctest.app.irc;

import com.ircclouds.irc.api.Callback;
import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.IRCApiImpl;
import com.ircclouds.irc.api.IServerParameters;
import com.ircclouds.irc.api.domain.IRCChannel;
import com.ircclouds.irc.api.state.IIRCState;

/**
 * Created by alexandre on 01/07/15.
 */
public final class IRCConnectConnectServiceImpl implements IRCConnectService {

    private IRCApi api = new IRCApiImpl(false);

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


//    @Override
    public void sendMessage(String target, String message) {
        api.message(target, message);
    }

}
