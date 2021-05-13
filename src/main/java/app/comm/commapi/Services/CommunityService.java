package app.comm.commapi.Services;

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

}
