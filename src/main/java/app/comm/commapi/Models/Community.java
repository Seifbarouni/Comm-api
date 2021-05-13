package app.comm.commapi.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "community", uniqueConstraints = { @UniqueConstraint(name = "name_unique", columnNames = { "name" }) })
public class Community {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "image_url", nullable = false)
    private String imgUrl;
    @Column(name = "members", nullable = false)
    private Long members;
    @Column(name = "about", nullable = false)
    private String about;
    @Column(name = "created", nullable = false)
    private String createdAt;

    public Community(String name, String imgUrl, Long members, String date, String about) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.members = members;
        this.createdAt = date;
        this.about = about;
    }

    public Community() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getMembers() {
        return members;
    }

    public void setMembers(Long members) {
        this.members = members;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
