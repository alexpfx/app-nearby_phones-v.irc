package br.com.alexpfx.irctest.app;

/**
 * Created by alexandre on 28/06/15.
 */
public interface IrcBotListener {

    public void onIrcBotConnect(String tag);

    void onIrcBotDisconnect(String tag);

    public static class NullListerner implements IrcBotListener{


        @Override
        public void onIrcBotConnect(String tag) {

        }

        @Override
        public void onIrcBotDisconnect(String tag) {

        }

    }



}
