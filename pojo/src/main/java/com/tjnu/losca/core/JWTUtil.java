package com.tjnu.losca.core;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tjnu.losca.pojo.UmsUser;
import com.auth0.jwt.JWT;
/**
 * @author Losca
 * @date 2022/2/5 12:24
 */
public class JWTUtil {
    private static final String KEY = "zuolinkai";
    public static String create(UmsUser umsUser){
        return JWT.create().withClaim("id", umsUser.getId())
                .withClaim("phone", umsUser.getPhone())
                .withClaim("email", umsUser.getEmail())
                .withClaim("name", umsUser.getName())
                .sign(Algorithm.HMAC256(KEY));

    }
    public static UmsUser decoder(String jwt) throws Exception {
        try {
            DecodedJWT decoder = JWT.require(Algorithm.HMAC256(KEY))
                    .build().verify(jwt);
            Long id = decoder.getClaim("id").asLong();
            String phone = decoder.getClaim("phone").asString();
            String email = decoder.getClaim("email").asString();
            String name = decoder.getClaim("name").asString();
            UmsUser umsUser = new UmsUser();
            umsUser.setId(id);
            umsUser.setPhone(phone);
            umsUser.setEmail(email);
            umsUser.setName(name);
            return umsUser;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("用户认证出错");
        }
    }
}
