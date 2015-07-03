package br.com.alexpfx.irctest.app.irc;

import java.util.List;

/**
 * Created by alexandre on 02/07/15.
 */
public interface Channel {
    String getName();

    String getTopicName();

    String getTopicAutor();

    List<String> getUsers();
}
