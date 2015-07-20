package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases;

import br.com.alexpfx.irctest.app.mvp.model.domain.irc.ChannelInfo;

/**
 * Created by alexandre on 05/07/15.
 */
public interface JoinChannelUseCase extends Interactor {

    void execute(String channel, Callback callback);

    public interface Callback {
        void onJoinChannelSuccess(ChannelInfo channelInfo);

        void onJoinChannelFail(Throwable t);
    }

}
