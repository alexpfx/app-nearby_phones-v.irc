package br.com.alexpfx.irctest.app.mvp.model.domain.audio;

import br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases.Interactor;

/**
 * Created by alexandre on 21/07/15.
 */
public interface RecorderUseCase extends Interactor {

    void init ();
    void startRecord ();
    void stopRecord ();


    interface Listener {
        void audioRecorded (byte [] buffer);
    }


}
