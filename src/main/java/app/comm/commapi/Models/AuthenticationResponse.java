package app.comm.commapi.Models;

public class AuthenticationResponse {
    private String message;
    private String jwt;
    private String username;
    private String email;
    private String bio;
    private Long followers;
    private Long following;
    private Long userId;

    public AuthenticationResponse(String message, String jwt, String username, String email, String bio, Long followers,
            Long following, Long userId) {
        this.message = message;
        this.jwt = jwt;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.followers = followers;
        this.following = following;
        this.userId = userId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getFollowers() {
        return followers;
    }

    public void setFollowers(Long followers) {
        this.followers = followers;
    }

    public Long getFollowing() {
        return following;
    }

    public void setFollowing(Long following) {
        this.following = following;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
