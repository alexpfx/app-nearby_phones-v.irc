package br.com.alexpfx.irctest.app.mvp.model.domain.irc.usecases;

/**
 * Created by alex on 11/07/2015.
 */
public interface SendMessageUseCase extends Interactor {

    void execute(String user, String message);

}
