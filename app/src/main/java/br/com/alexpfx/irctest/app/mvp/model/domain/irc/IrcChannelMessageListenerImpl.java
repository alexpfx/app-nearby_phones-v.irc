package br.com.alexpfx.irctest.app.mvp.model.domain.irc;

import com.ircclouds.irc.api.domain.messages.*;
import com.ircclouds.irc.api.domain.messages.interfaces.IMessage;

/**
 * Created by alexandre on 22/07/15.
 */
public class IrcChannelMessageListenerImpl implements IrcChannelMessageListener {

    @Override
    public void onChannelMessage(ChannelPrivMsg aMsg) {

    }

    @Override
    public void onChannelJoin(ChanJoinMessage aMsg) {

    }

    @Override
    public void onChannelPart(ChanPartMessage aMsg) {

    }

    @Override
    public void onChannelNotice(ChannelNotice aMsg) {

    }

    @Override
    public void onChannelAction(ChannelActionMsg aMsg) {

    }

    @Override
    public void onChannelKick(ChannelKick aMsg) {

    }

    @Override
    public void onTopicChange(TopicMessage aMsg) {

    }

    @Override
    public void onUserPrivMessage(UserPrivMsg aMsg) {

    }

    @Override
    public void onUserNotice(UserNotice aMsg) {

    }

    @Override
    public void onUserAction(UserActionMsg aMsg) {

    }

    @Override
    public void onServerNumericMessage(ServerNumericMessage aMsg) {

    }

    @Override
    public void onServerNotice(ServerNotice aMsg) {

    }

    @Override
    public void onNickChange(NickMessage aMsg) {

    }

    @Override
    public void onUserQuit(QuitMessage aMsg) {

    }

    @Override
    public void onError(ErrorMessage aMsg) {

    }

    @Override
    public void onChannelMode(ChannelModeMessage aMsg) {

    }

    @Override
    public void onUserPing(UserPing aMsg) {

    }

    @Override
    public void onUserVersion(UserVersion aMsg) {

    }

    @Override
    public void onServerPing(ServerPing aMsg) {

    }

    @Override
    public void onMessage(IMessage aMessage) {


    }
}
