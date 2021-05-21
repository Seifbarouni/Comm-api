package app.comm.commapi.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "community", nullable = false)
    private String community;
    @Column(name = "username", nullable = false)
    private String user;
    @Column(name = "created", nullable = false)
    private String createdAt;
    @Column(name = "textContent", nullable = false)
    private String textContent;
    @Column(name = "image", nullable = true, length = 1000)
    private byte[] image;
    @Column(name = "video", nullable = true, length = 5000)
    private byte[] video;
    @Column(name = "likes", nullable = false)
    private Long likes;
    @Column(name = "comments", nullable = false)
    private Long comments;

    public Post(String community, String user, String createdAt, String textContent, byte[] image, byte[] video,
            Long likes, Long comments) {
        this.community = community;
        this.user = user;
        this.createdAt = createdAt;
        this.textContent = textContent;
        this.image = image;
        this.video = video;
        this.likes = likes;
        this.comments = comments;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

}
