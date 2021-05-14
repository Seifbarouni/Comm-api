package app.comm.commapi.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.comm.commapi.Models.Community;
import app.comm.commapi.Models.Joins;
import app.comm.commapi.Services.CommunityService;
import app.comm.commapi.Services.JoinsService;

@RestController
@CrossOrigin(origins = "http://localhost:5000", exposedHeaders = "**")
public class CommunityController {
    @Autowired
    private CommunityService communityService;
    @Autowired
    private JoinsService joinsService;

    @GetMapping("/mycommunities/{id}")
    public List<Community> getCommunitiesByUserId(@PathVariable(name = "id") Long id) {
        Optional<List<Joins>> userJoins = joinsService.getCommunitiesByUserId(id);
        List<Community> communitiesByUser = new ArrayList<>();
        if (userJoins.isPresent()) {
            for (Joins join : userJoins.get()) {
                try {
                    Community comm = communityService.getCommunityById(join.getCommunityId()).get();
                    communitiesByUser.add(comm);
                } catch (Exception e) {
                    System.out.println("community not found");
                }
            }
        }
        return communitiesByUser;
    }

    @GetMapping("/community/{name}")
    public Community getCommunityByName(@PathVariable(name = "name") String name) {
        Optional<Community> comm = communityService.getCommunityByName(name);
        if (comm.isPresent())
            return comm.get();
        return null;
    }

    @GetMapping("/explore/{userId}")
    public List<Community> getExploreCommunitiesSection(@PathVariable(name = "userId") Long id) {
        List<Community> exploreCommunities = new ArrayList<>();
        Optional<List<Joins>> userJoins = joinsService.getCommunitiesByUserId(id);
        if (!userJoins.get().isEmpty()) {
            List<Long> idsOfJoinedCommunities = new ArrayList<Long>();
            for (Joins jo : userJoins.get()) {
                idsOfJoinedCommunities.add(jo.getCommunityId());
            }
            exploreCommunities = communityService.getExploreSection(idsOfJoinedCommunities);
        } else {
            exploreCommunities = communityService.getAllCommunities();
        }
        Collections.shuffle(exploreCommunities);
        return exploreCommunities;
    }

}
