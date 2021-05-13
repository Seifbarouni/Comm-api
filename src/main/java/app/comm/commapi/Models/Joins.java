package app.comm.commapi.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "joins")
@IdClass(JoinsId.class)
public class Joins {
    @Id
    private Long communityId;
    @Id
    private Long userId;

    public Joins(Long communityId, Long userId) {
        this.communityId = communityId;
        this.userId = userId;
    }

    public Joins() {
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

}
