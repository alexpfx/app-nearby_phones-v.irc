package br.com.alexpfx.irctest.app.mvp.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by alexandre on 04/07/15.
 */
public class UserIdentify implements Identity {
    private String nickname;
    private String alternative;
    private String name;
    private String email;

    private UserIdentify(Builder builder) {
        nickname = builder.nickname;
        alternative = builder.alternative;
        name = builder.name;
        email = builder.email;
    }

    public static final class Builder {
        private String nickname;
        private String alternative;
        private String name;
        private String email;

        public Builder() {
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder alternative(String alternative) {
            this.alternative = alternative;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public UserIdentify build() {
            return new UserIdentify(this);
        }
    }

    @NonNull
    public String getNickname() {
        return nickname;
    }

    @Nullable
    public String getAlternative() {
        return alternative;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getEmail() {
        return email;
    }
}
