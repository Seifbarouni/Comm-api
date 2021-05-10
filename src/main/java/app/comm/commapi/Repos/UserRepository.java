package app.comm.commapi.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.comm.commapi.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
