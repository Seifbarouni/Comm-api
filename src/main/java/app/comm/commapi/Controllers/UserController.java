package app.comm.commapi.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.comm.commapi.Models.AuthenticationRequest;
import app.comm.commapi.Models.AuthenticationResponse;
import app.comm.commapi.Models.MyUserDetails;
import app.comm.commapi.Models.User;
import app.comm.commapi.Models.UserRegisterModel;
import app.comm.commapi.Services.CustomUserDetailsService;
import app.comm.commapi.Services.UserService;
import app.comm.commapi.util.JwtUtil;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String test() {
        return "yo";
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody UserRegisterModel userRegisterModel) {
        Optional<User> us = userService.getUserByUsername(userRegisterModel.getUsername());
        if (us.isPresent())
            return new AuthenticationResponse("username already exists", "empty", "", "", "", 0L, 0L, 0L);
        try {
            User user = new User(userRegisterModel.getUsername(), userRegisterModel.getEmail(),
                    passwordEncoder.encode(userRegisterModel.getPassword()), "", 0L, 0L);
            userService.addUser(user);
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (Exception e) {
        }
        final MyUserDetails userDetails = customUserDetailsService.loadUserByUsername(userRegisterModel.getUsername());
        return new AuthenticationResponse("Success",
                jwtUtil.generateToken(userRegisterModel.getUsername(), userDetails), userDetails.getUsername(),
                userDetails.getEmail(), userDetails.getBio(), 0L, 0L, userDetails.getId());

    }

    @PostMapping("/auth")
    public AuthenticationResponse auth(@RequestBody AuthenticationRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception ex) {
            return new AuthenticationResponse("Error : invalid username or password", "empty", "", "", "", 0L, 0L, 0L);
        }
        final MyUserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());
        return new AuthenticationResponse("Success", jwtUtil.generateToken(authRequest.getUsername(), userDetails),
                userDetails.getUsername(), userDetails.getEmail(), userDetails.getBio(), userDetails.getFollowers(),
                userDetails.getFollowing(), userDetails.getId());
    }
}
