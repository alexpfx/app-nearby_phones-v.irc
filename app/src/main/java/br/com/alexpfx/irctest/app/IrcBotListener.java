package br.com.alexpfx.irctest.app;

/**
 * Created by alexandre on 28/06/15.
 */
public interface IrcBotListener {

    public void onIrcBotConnect(IrcBot ircBot);

    void onIrcBotDisconnect(IrcBot ircBot);

    public static class NullListerner implements IrcBotListener{


        @Override
        public void onIrcBotConnect(IrcBot ircBot) {

        }

        @Override
        public void onIrcBotDisconnect(IrcBot ircBot) {

        }

    }



}
