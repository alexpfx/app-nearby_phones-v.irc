package br.com.alexpfx.irctest.app.mvp.model.interactor;

/**
 * Created by alex on 12/07/2015.
 */
public interface HandshakeUseCase extends Interactor {

    void execute (String canal, Callback callback);

    public interface Callback {

    }


}
