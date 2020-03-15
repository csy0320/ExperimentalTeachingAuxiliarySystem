package cn.jjdcn.etas.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class MyJwtUtil {

    /**
     * 生成JWT
     *
     * @param id
     * @param subject
     * @return
     */
    public static String createJWT(Long id, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(String.valueOf(id))
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, "key924951774key924951774key924951774key924951774");
            builder.setExpiration( new Date( nowMillis + 24*60*60*1000));

        return builder.compact();
    }

    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public static Claims parseJWT(String jwtStr){
        return  Jwts.parser()
                .setSigningKey("key924951774key924951774key924951774key924951774")
                .parseClaimsJws(jwtStr)
                .getBody();
    }

}
