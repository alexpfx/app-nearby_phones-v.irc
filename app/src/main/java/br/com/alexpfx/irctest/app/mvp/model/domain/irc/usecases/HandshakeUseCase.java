package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases;

/**
 * Created by alex on 12/07/2015.
 */
public interface HandshakeUseCase extends Interactor {

    void execute(String canal, Callback callback);

    public interface Callback {

    }

}
