package br.com.alexpfx.irctest.app;

import android.util.Log;
import org.apache.commons.lang3.StringUtils;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 27/06/15.
 */
public class ConversationBot extends PircBot {
    public static final int nr_pessoas_canal = 150;
    public static final String server_url = "irc.brasirc.org";
    public static final String nick = "LoiraMaquira";
    public static final String userLogin = "spoor";

    String tag = ConversationBot.class.getName();

    public static interface BotListener {
        void receiveBotMessage(String msg, String count);
    }

    private BotListener listener;

    private List<String> pool = new ArrayList<String>();

    public ConversationBot(BotListener listener) {
        setVersion("v5");
        setName(nick);
        setLogin(userLogin);
        setVerbose(false);
        this.listener = listener;

    }

    public static String getServer_url() {
        return server_url;
    }

    private String[] msgs = new String[]{
            "yes", "no", "maybe", "heheeheh", "where are you talking?", "how old are you?", "hi", "hello!"
    };

    private String[] msgGeral = new String[]{
            "sim", "nao", "talvez", "heheeheh", "tc de onde?", "qtos anos?", "oi", "oi, tudo bem?!", "ahaahah", "legal :)"
    };

    private String[] msgAns = new String[]{
            "sim =)", "nao :)", "talvez ;)"
    };

    private String[] msgAnsIdade = new String[]{
            "19", "19 anos"
    };

    private String[] msgAnsNr = new String[]{
            "legal", "velho", "novo", "experiente heehhehe"
    };

    private String[] msgAnsOi = new String[]{
            "ola", "ola tudo bem?"
    };

    private String[] msgAnsTcDeOnde = new String[]{
            "Mg", "Mg e vc"
    };

    private String[] msgAnsSolteir = new String[]{
            "Solteira e vc", "Solteira"
    };

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        countMsgs++;
        String moreInfo = sender + ", voce é o " + countMsgs + " a mandar mensagem para os canais q eu estou.";
        final String backMsg = getMessage(moreInfo);
        Log.i(tag, "" + countMsgs + " " + channel + " " + sender);
        if (countMsgs % 100 == 0) {
            sendMessage(channel, backMsg);
        }
    }

    private String getMessage(String moreInfo) {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    @Override
    protected void onChannelInfo(String channel, int userCount, String topic) {
        if (userCount > nr_pessoas_canal) {
            Log.i(tag, "join channel " + channel);
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            joinChannel(channel);
        }
    }

    @Override
    protected void onDisconnect() {
        try {
            Thread.sleep(20000);
            reconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private SecureRandom random = new SecureRandom();

    @Override
    protected void onConnect() {
        Log.i(tag, "connected");
        listChannels();

    }

    @Override
    protected void onPrivateMessage(String sender, String login, String hostname, String message) {
        Log.i(tag, "private");
        Log.i(tag, "sender: " + sender);
        Log.i(tag, "message: " + message);
        countPrivate++;
        String info = "#" + countPrivate;
        listener.receiveBotMessage(message, String.valueOf(countPrivate));
        final String privateMessage = getPrivateMessage(message, info);
        Log.i(tag, "resposta: " + privateMessage);
        sendMessage(sender, privateMessage);
    }

    StringBuilder sb = new StringBuilder();

    int countPrivate = 0;
    int countMsgs = 0;

    private String getPrivateMessage(String message, String moreInfo) {
        if (StringUtils
                .containsAny(message, "vinda", "vindo", "#", "canal", "VirtuaLife", "www", "visite", "proibido")) {
            return "";
        }
        if (StringUtils.containsAny(message, "oi", "ola", "tudo bem", "bom dia")) {
            return getRandomFrom(msgAnsOi);
        } else if (StringUtils.containsAny(message, "idade", "anos")) {
            return getRandomFrom(msgAnsIdade);
        } else if (StringUtils.containsAny(message, "onde", "tc de", "tecla")) {
            return getRandomFrom(msgAnsTcDeOnde);
        } else if (StringUtils.containsAny(message, "solteira", "namorado")) {
            return getRandomFrom(msgAnsSolteir);
        } else if (StringUtils.containsAny(message, "?")) {
            return getRandomFrom(msgAns);
        } else if (StringUtils.isAlphanumeric(message)) {
            return getRandomFrom(msgAnsNr);
        } else {
            return getRandomFrom(msgGeral);
        }
    }

    public synchronized String getFromPool() {
        final int size = pool.size();
        if (size > 0) {
            Log.i("poolSize", String.valueOf(size));
            return pool.remove(0);
        }
        return "";
    }

    public synchronized void addToPool(String... sss) {
        StringBuilder sb = new StringBuilder();
        for (String s : sss) {
            sb.append(s).append(" ");
        }
        pool.add(sb.toString());
    }

    String getRandomFrom(String msg[]) {
        final int n = random.nextInt(msg.length);
        return msg[n];
    }

}
