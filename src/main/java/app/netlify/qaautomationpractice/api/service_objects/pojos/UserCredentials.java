package app.netlify.qaautomationpractice.api.service_objects.pojos;
/*
Author: Daniel Martins
Email: daniel.d.martins@outlook.com
*/
public class UserCredentials {
    private String username;
    private String password;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserCredentials() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "UserCredentials {username=%s, password=%s}",
                this.username,
                this.password);
    }
}
