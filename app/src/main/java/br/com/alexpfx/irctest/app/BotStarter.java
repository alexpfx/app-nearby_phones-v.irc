package br.com.alexpfx.irctest.app;

import android.os.AsyncTask;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import java.io.IOException;

/**
 * Created by alexandre on 28/06/15.
 */
public class BotStarter {

    private PircBot bot;

    public BotStarter(PircBot bot) {
        this.bot = bot;
    }

    public void connect(String ircServer) {
        StartBotTask task = new StartBotTask(ircServer);
        task.execute();
    }

    private class StartBotTask extends AsyncTask<String, Void, Boolean> {
        private String ircServer;

        public StartBotTask(String ircServer) {
            this.ircServer = ircServer;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                if (!bot.isConnected()){
                    bot.connect(ircServer);
                }
            } catch (IOException e) {

                e.printStackTrace();
            } catch (IrcException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

}
