package app.comm.commapi.Models;

import java.io.Serializable;

public class FollowsId implements Serializable {
    private Long followerId;
    private Long followedId;

    public FollowsId(Long followerId, Long followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public FollowsId() {
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
