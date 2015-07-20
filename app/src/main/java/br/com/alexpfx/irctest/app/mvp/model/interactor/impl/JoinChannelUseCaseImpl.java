package br.com.alexpfx.irctest.app.mvp.model.interactor.impl;

import br.com.alexpfx.irctest.app.mvp.model.irc.IRCApiSingleton;
import br.com.alexpfx.irctest.app.mvp.model.ChannelInfo;
import br.com.alexpfx.irctest.app.mvp.model.UserIdentify;
import br.com.alexpfx.irctest.app.mvp.model.interactor.JoinChannelUseCase;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.Executor;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.MainThread;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.MainThreadImpl;
import br.com.alexpfx.irctest.app.mvp.model.interactor.executor.ThreadExecutor;
import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.domain.IRCChannel;
import com.ircclouds.irc.api.domain.IRCUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 05/07/15.
 */
/* todo mundo que esta no canal recebe as notificacoes */
public class JoinChannelUseCaseImpl implements JoinChannelUseCase {
    private Executor executor = ThreadExecutor.ThreadExecutorSingleton.INSTANCE.get();
    private IRCApi ircApi = IRCApiSingleton.INSTANCE.get();
    private MainThread mainThread = MainThreadImpl.MainThreadSingleton.INSTANCE.get();
    private String channel;
    private Callback callback;

    @Override
    public void execute(String channel, Callback callback) {
        this.channel = channel;
        this.callback = callback;
        executor.run(this);
    }

    @Override
    public void run() {
        ircApi.joinChannel(channel, new com.ircclouds.irc.api.Callback<IRCChannel>() {
            @Override
            public void onSuccess(IRCChannel aObject) {
                notifySuccess(aObject);
            }

            @Override
            public void onFailure(Exception aExc) {
                notifyFail(aExc);
            }
        });
    }

    private void notifyFail(final Exception aExc) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onJoinChannelFail(aExc);
            }
        });

    }

    private void notifySuccess(final IRCChannel ircChannel) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onJoinChannelSuccess(new ChannelInfo() {

                    @Override
                    public String getChannelName() {
                        return ircChannel.getName();
                    }

                    @Override
                    public List<UserIdentify> getUsers() {
                        final List<IRCUser> ircUsers = ircChannel.getUsers();

                        List<UserIdentify> userList = new ArrayList<UserIdentify>();
                        for (IRCUser ircUser : ircUsers) {
                            final UserIdentify user = new UserIdentify.Builder().name(ircUser.getIdent()).nickname(ircUser.getNick()).email(ircUser.getHostname()).build();
                            userList.add(user);
                        }
                        return userList;
                    }
                });

            }
        });

    }
}
