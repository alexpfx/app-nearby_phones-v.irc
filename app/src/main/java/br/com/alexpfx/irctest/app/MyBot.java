package br.com.alexpfx.irctest.app;

import android.os.Environment;
import android.util.Log;
import android.util.Property;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by alexandre on 27/06/15.
 */
public class MyBot extends PircBot {
    String tag = MyBot.class.getName();


    private List<String> pool = new ArrayList<String>();
    String names[] = new String[]{"LoiraAtrai"};


    public MyBot() {
        setVersion("v5");
        setName(names[random.nextInt(names.length)]);
        setLogin(names[random.nextInt(names.length)]);
        setVerbose(false);

    }


    private String[] msgs = new String[]{
            "NO",
            "YES"
    };

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        countMsgs++;
        String moreInfo = sender + ", voce é o " + countMsgs + " a mandar mensagem para os canais q eu estou.";
        final String backMsg = getMessage(moreInfo);
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
        if (userCount > 20) {
            Log.i(tag, "join channel "+channel);
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
        String info = "voce eh o #" + countPrivate + " a me contactar hj";
        sendMessage(sender, getPrivateMessage(info));
    }

    StringBuilder sb = new StringBuilder();

    int countPrivate = 0;
    int countMsgs = 0;

    private String getPrivateMessage(String moreInfo) {
        sb.setLength(0);
        sb.append("No momento nao estou. Deixe seu recado e telefone para contato. ");
        sb.append(moreInfo);
        return sb.toString();
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


}
