package br.com.alexpfx.irctest.app.mvp.model;

import android.support.annotation.NonNull;

/**
 * Created by alexandre on 04/07/15.
 */
public class ServerIdentity implements Identity {
    private String ircServer;
    private String port;
    private String password;

    private ServerIdentity(Builder builder) {
        ircServer = builder.ircServer;
        port = builder.port;
        password = builder.password;
    }

    public static final class Builder {
        private String ircServer;
        private String port;
        private String password;

        public Builder() {
        }

        public Builder ircServer(String ircServer) {
            this.ircServer = ircServer;
            return this;
        }

        public Builder port(String port) {
            this.port = port;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public ServerIdentity build() {
            return new ServerIdentity(this);
        }
    }

    @NonNull
    public String getIrcServer() {
        return ircServer;
    }

    public String getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }
}
