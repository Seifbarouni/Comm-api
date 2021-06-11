package app.comm.commapi.Models;

import java.io.Serializable;

public class LikesId implements Serializable {
    private Long postId;
    private Long userId;

    public LikesId(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public LikesId() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
