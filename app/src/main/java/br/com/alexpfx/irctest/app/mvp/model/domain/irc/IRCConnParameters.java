package br.com.alexpfx.irctest.app.mvp.model.domain.irc;

/**
 * Created by alexandre on 02/07/15.
 */
public class IRCConnParameters {

    private String hostname;
    private String ident = "alexpfx";
    private String nickName;
    private String login = "alexpfx";
    private String alternativeNickName = "booooot";

    private IRCConnParameters(Builder builder) {
        hostname = builder.hostname;
        ident = builder.ident != null ? builder.ident : ident;
        nickName = builder.nickName;
        login = builder.login != null ? builder.ident : ident;
        alternativeNickName = builder.alternativeNickName != null ? builder.alternativeNickName : alternativeNickName;
    }

    public String getHostname() {
        return hostname;
    }

    public String getIdent() {
        return ident;
    }

    public String getNickName() {
        return nickName;
    }

    public String getLogin() {
        return login;
    }

    public String getAlternativeNickName() {
        return alternativeNickName;
    }

    public static final class Builder {
        private String hostname;
        private String ident;
        private String nickName;
        private String login;
        private String alternativeNickName;

        public Builder(String hostname, String nickName, String alternativeNickName) {
            this.nickName = nickName;
            this.hostname = hostname;
            this.alternativeNickName = alternativeNickName;
        }

        public Builder ident(String ident) {
            this.ident = ident;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public IRCConnParameters build() {
            return new IRCConnParameters(this);
        }
    }
}
