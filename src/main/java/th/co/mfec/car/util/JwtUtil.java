package th.co.mfec.car.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import th.co.mfec.car.constant.StatusCode;
import th.co.mfec.car.exception.BaseException;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;
    @Value("${app.jwt.expired}")
    private String expired;
    
    public String generateToken(String username) {
        Date expiryDate = new Date(System.currentTimeMillis() + 1000 * 60 *Integer.parseInt(expired));
        String token = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
        return token;
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        }
        catch(ExpiredJwtException ex) {
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_CODE_401);
        }
        catch(Exception ex) {
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_CODE_401);
        }

    }

    public String getUsernameFromJwt(String token) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
