package app.comm.commapi.Models;

public class AuthenticationResponse {
    private String message;
    private String jwt;

    public AuthenticationResponse(String message, String jwt) {
        this.message = message;
        this.jwt = jwt;
    }

    public AuthenticationResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
