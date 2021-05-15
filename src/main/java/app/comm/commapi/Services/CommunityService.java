package app.comm.commapi.Services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.comm.commapi.Models.Community;
import app.comm.commapi.Repos.CommunityRepository;

@Service
public class CommunityService {
    @Autowired
    private CommunityRepository communityRepository;

    public Optional<Community> getCommunityById(Long id) {
        return communityRepository.findById(id);
    }

    public Optional<Community> getCommunityByName(String name) {
        return communityRepository.findByName(name);
    }

    public List<Community> getExploreSection(List<Long> ids) {
        return communityRepository.findExploreSection(ids);
    }

    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    public String addMemberToCommunity(Long communityId) {
        Optional<Community> optComm = communityRepository.findById(communityId);
        if (optComm.isPresent()) {
            Community comm = optComm.get();
            comm.setMembers(comm.getMembers() + 1);
            comm = communityRepository.save(comm);
            if (comm != null)
                return "Success";
            return "Error";
        }
        return "Error";
    }

    public String deleteMemberFromCommunity(Long communityId) {
        Optional<Community> optComm = communityRepository.findById(communityId);
        if (optComm.isPresent()) {
            Community comm = optComm.get();
            if (comm.getMembers() > 0L) {
                comm.setMembers(comm.getMembers() - 1);
                comm = communityRepository.save(comm);
            }
            if (comm != null)
                return "Success";
            return "Error";
        }
        return "Error";
    }

}
