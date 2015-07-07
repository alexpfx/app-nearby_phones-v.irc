package br.com.alexpfx.irctest.app.mvp.model.interactor;

import br.com.alexpfx.irctest.app.mvp.model.UserIdentify;

import java.util.List;

/**
 * Created by alexandre on 07/07/15.
 */
public interface NotifyUsersUseCase extends Interactor{

    void execute (List<UserIdentify> userList, Callback callback);

    interface Callback {

    }


}
