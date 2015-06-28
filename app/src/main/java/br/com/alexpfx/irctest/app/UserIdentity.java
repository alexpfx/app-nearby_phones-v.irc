package br.com.alexpfx.irctest.app;

/**
 * Created by alexandre on 28/06/15.
 */
public class UserIdentity {
    private final String name;
    private final String login;
    private final String messagePrefix;

    public UserIdentity(String name, String login, String messagePrefix) {
        this.name = name;
        this.login = login;
        this.messagePrefix = messagePrefix;
    }

    public UserIdentity(String name, String login) {
        this.name = name;
        this.login = login;
        messagePrefix = "$$$$";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserIdentity that = (UserIdentity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(login != null ? !login.equals(that.login) : that.login != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getMessagePrefix() {
        return messagePrefix;
    }
}
