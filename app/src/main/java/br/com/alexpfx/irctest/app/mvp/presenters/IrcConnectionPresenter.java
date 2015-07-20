package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.mvp.model.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentity;

/**
 * Created by alexandre on 04/07/15.
 */
public interface IrcConnectionPresenter {

    void connect(UserIdentity userIdentity, ServerIdentity serverIdentity);

    void disconnect();

}
