package app.comm.commapi.Models;

public class AuthenticationRequest {
    private String username;
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequest() {
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
