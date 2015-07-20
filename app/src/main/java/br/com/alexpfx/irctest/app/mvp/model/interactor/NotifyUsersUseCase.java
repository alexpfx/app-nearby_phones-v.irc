package br.com.alexpfx.irctest.app.mvp.model.interactor;

import br.com.alexpfx.irctest.app.mvp.model.UserIdentity;

import java.util.List;

/**
 * Created by alexandre on 07/07/15.
 */
public interface NotifyUsersUseCase extends Interactor {

    void execute(List<UserIdentity> userList, Callback callback);

    interface Callback {

    }


}
