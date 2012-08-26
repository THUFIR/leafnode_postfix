package net.bounceme.dur.leafnode_postfix;

public class UserHost {

    private String user;
    private String host;
    private String password = "password";
    private boolean debug;

    public UserHost(String user, String host, String password) {
        this.user = user;
        this.host = host;
        this.password = password;
        debug = false;
    }

    public UserHost(String user, String host) {
        this.user = user;
        this.host = host;
        debug = false;
    }

    public UserHost(String user, String host, boolean debug) {
        this.user = user;
        this.host = host;
        this.debug = debug;
    }

    @Override
    public String toString() {
        return getUser() + getHost() + isDebug();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
