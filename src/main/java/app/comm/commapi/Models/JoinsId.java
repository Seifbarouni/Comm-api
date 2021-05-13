package app.comm.commapi.Models;

import java.io.Serializable;

public class JoinsId implements Serializable {
    private Long communityId;
    private Long userId;

    public JoinsId(Long communityId, Long userId) {
        this.communityId = communityId;
        this.userId = userId;
    }

    public JoinsId() {
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
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
