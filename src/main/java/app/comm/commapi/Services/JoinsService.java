package app.comm.commapi.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.comm.commapi.Models.Joins;
import app.comm.commapi.Repos.JoinsRepository;

@Service
public class JoinsService {
    @Autowired
    private JoinsRepository joinsRepository;
    @Autowired
    private CommunityService communityService;

    public Optional<List<Joins>> getCommunitiesByUserId(Long userId) {
        return joinsRepository.findByUserId(userId);
    }

    public String joinCommunity(Long communityId, Long userId) {
        Joins j = joinsRepository.save(new Joins(communityId, userId));
        String res = communityService.addMemberToCommunity(communityId);
        if (j != null && res.equals("Success"))
            return "Success";
        return "Error";
    }

    public String leaveCommunity(Long communityId, Long userId) {
        joinsRepository.deleteJoin(userId, communityId);
        return communityService.deleteMemberFromCommunity(communityId);
    }
}
