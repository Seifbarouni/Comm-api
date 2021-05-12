package app.comm.commapi.util;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import app.comm.commapi.Models.MyUserDetails;

@Service
public class JwtUtil {
    private final String SECRET_KEY = "asdfSFS34wfsdfsdfSDSxxxxxxxxxD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractId(String token) {
        return "";
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, MyUserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 15000000))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(Charset.forName("UTF-8")))).compact();
    }

    public Boolean validateToken(String token, MyUserDetails user) {
        final String username = extractUsername(token);

        Claims jwsMap = Jwts.parser().setSigningKey(SECRET_KEY.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token)
                .getBody();
        // String email = (String) jwsMap.get("email");
        Long id = ((Integer) jwsMap.get("userId")).longValue();

        return (username.equals(user.getUsername()) && !isTokenExpired(token) && user.getId() == id);
    }
}
