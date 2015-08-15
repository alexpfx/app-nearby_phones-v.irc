package br.com.alexpfx.irctest.app.mvp.presenters;

import br.com.alexpfx.android.lib.network.irc.ServerIdentity;
import br.com.alexpfx.android.lib.network.irc.UserIdentity;

/**
 * Created by alexandre on 04/07/15.
 */
public interface IrcConnectionPresenter {

    void connect(UserIdentity userIdentity, ServerIdentity serverIdentity);

    void disconnect();

}
