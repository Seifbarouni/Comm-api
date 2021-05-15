package app.comm.commapi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.comm.commapi.Services.JoinsService;

@RestController
@CrossOrigin(origins = "http://localhost:5000", exposedHeaders = "**")
public class JoinsController {
    @Autowired
    private JoinsService joinsService;

    @GetMapping("/j/joinCommunity/{communityId}/{userId}")
    public String joinCommunity(@PathVariable("communityId") Long communityId, @PathVariable("userId") Long userId) {
        return joinsService.joinCommunity(communityId, userId);
    }

    @GetMapping("/j/leaveCommunity/{communityId}/{userId}")
    public String leaveCommunity(@PathVariable("communityId") Long communityId, @PathVariable("userId") Long userId) {
        return joinsService.leaveCommunity(communityId, userId);
    }
}
