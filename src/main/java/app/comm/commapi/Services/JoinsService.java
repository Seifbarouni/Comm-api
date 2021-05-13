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

    public Optional<List<Joins>> getCommunitiesByUserId(Long userId) {
        return joinsRepository.findByUserId(userId);
    }

}
