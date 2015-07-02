package br.com.alexpfx.irctest.app.irc;

import com.ircclouds.irc.api.IServerParameters;
import com.ircclouds.irc.api.domain.IRCServer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alexandre on 02/07/15.
 */
public class IRCServiceUtils {

    public static IServerParameters getServerParameters(final String hostname, final String ident, final String realname, final String nick, final String... alternativeNicks) {
        return new IServerParameters() {
            @Override
            public String getNickname() {
                return nick;
            }

            @Override
            public List<String> getAlternativeNicknames() {
                return Arrays.asList(alternativeNicks);
            }

            @Override
            public String getIdent() {
                return ident;
            }

            @Override
            public String getRealname() {
                return realname;
            }

            @Override
            public IRCServer getServer() {
                return new IRCServer(hostname);
            }
        };
    }

}
