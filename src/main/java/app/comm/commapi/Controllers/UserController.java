package app.comm.commapi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.comm.commapi.Models.AuthenticationRequest;
import app.comm.commapi.Models.AuthenticationResponse;
import app.comm.commapi.Models.MyUserDetails;
import app.comm.commapi.Services.CustomUserDetailsService;
import app.comm.commapi.util.JwtUtil;

@RestController
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/")
    public String test() {
        return "yo";
    }

    @PostMapping("/auth")
    public AuthenticationResponse auth(@RequestBody AuthenticationRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception ex) {
            return new AuthenticationResponse("Error : invalid username or password", "empty");
        }
        final MyUserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());
        return new AuthenticationResponse("Success", jwtUtil.generateToken(authRequest.getUsername(), userDetails));
    }
}
