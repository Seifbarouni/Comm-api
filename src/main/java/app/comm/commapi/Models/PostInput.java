package app.comm.commapi.Models;

public class PostInput {
    private String community;
    private String user;
    private String createdAt;
    private String textContent;

    public PostInput(String community, String user, String createdAt, String textContent) {
        this.community = community;
        this.user = user;
        this.createdAt = createdAt;
        this.textContent = textContent;
    }

    public PostInput() {
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

}
