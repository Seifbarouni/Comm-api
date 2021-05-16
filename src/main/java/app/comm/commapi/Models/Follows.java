package app.comm.commapi.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "follows")
@IdClass(FollowsId.class)
public class Follows {
    @Id
    private Long followerId;
    @Id
    private Long followedId;

    public Follows(Long followerId, Long followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public Long getFollowedId() {
        return followedId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Follows() {
    }
}
