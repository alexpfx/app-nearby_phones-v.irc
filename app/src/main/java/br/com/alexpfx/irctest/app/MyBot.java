package br.com.alexpfx.irctest.app;

import android.util.Log;
import org.jibble.pircbot.PircBot;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by alexandre on 27/06/15.
 */
public class MyBot extends PircBot {
    String tag = MyBot.class.getName();

    public MyBot() {
        setVersion("v1");
        setName("OliviaDelNero");
        setLogin("Garbin");
        setVerbose(false);

    }

    private String[] msgs = new String[]{
            "I don't know!",
            "why?",
            "explain...",
            "no",
            "yes",
            "ok",
            "pleeease",
            "don't",
            "of course not",
            "maybe",
            "yes"
    };

    private Random random = new Random();

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        final int length = msgs.length;
        final int n = random.nextInt(3);
        System.out.println(n);
        if (humanMessage != null && !humanMessage.isEmpty()) {
            sendMessage(channel, sender + ", " + humanMessage);
            humanMessage = "";
        }
        if (n == 1) {
            sendMessage(channel, sender + ", " + msgs[random.nextInt(length)]);
        }
    }

    private String humanMessage;

    public void setPersonalMessage(String msg) {
        this.humanMessage = msg;

    }
}
