package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.irctest.app.UserIdentity;
import br.com.alexpfx.irctest.app.mvp.model.ServerIdentity;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentify;

/**
 * Created by alexandre on 04/07/15.
 */
public interface IrcConnectionPresenter {

    void connect(UserIdentify userIdentity, ServerIdentity serverIdentity);

    void disconnect();

}
