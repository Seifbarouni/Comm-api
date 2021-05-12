package app.comm.commapi.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.comm.commapi.Models.User;
import app.comm.commapi.Repos.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
