package com.xc.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

//what why how com.xc.utils.jwt? -> https://jwt.io/introduction/
public class MyJWTUtil {
    /**
     * 使用HMAC256生成token
     *
     * @param secret 密码 unique
     * @return
     */
    public static String createToken(String secret, String issuer, String audience) {
        String token = null;
        try {
            // 1、对明文密码进行算法加密
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 2、对密码签名，生成token
            token = JWT.create().withIssuer(issuer).withAudience(audience).sign(algorithm);

        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    public static Boolean verifyToken(String token, String secret, String issuer, String audience) {
//        DecodedJWT decode = JWT.decode(token);
//        String signature = decode.getSignature();
//        String header = decode.getHeader();
//        String payload = decode.getPayload();
//        byte[] decode1 = Base64UrlCodec.BASE64.decode(payload);
//        String a = new String(decode1);
        try {
            // 选择一种算法
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build(); //Reusable verifier instance
            verifier.verify(token);
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String secret = "secret";
        String token = createToken(secret, "xc", "xc");
        Boolean aBoolean = verifyToken(token, secret, "xc", "xc");
        System.out.println(aBoolean);
    }
}
