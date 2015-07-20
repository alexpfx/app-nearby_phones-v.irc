package br.com.alexpfx.irctest.app.mvp.model.interactor;

/**
 * Created by alex on 11/07/2015.
 */
public interface SendMessageUseCase extends Interactor {

    void execute(String user, String message);

}
