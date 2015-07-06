package br.com.alexpfx.irctest.app.mvp.model.interactor;

import br.com.alexpfx.irctest.app.mvp.model.ChannelInfo;

/**
 * Created by alexandre on 05/07/15.
 */
public interface JoinChannelUseCase extends Interactor{

    void execute(String channel, Callback callback);

    public interface Callback {
        void onSuccess (ChannelInfo channelInfo);
        void onFailure (Throwable t);
    }


}
