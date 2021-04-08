package com.kintexgroup.hkpsi.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JWT工具类 总的来说，工具类中有三个方法 获取JwtToken，获取JwtToken中封装的信息，判断JwtToken是否存在 1.
 * encode()，参数是=签发人，存在时间，一些其他的信息=。返回值是JwtToken对应的字符串 2. decode()，参数是=JwtToken=。返回值是荷载部分的键值对 3.
 * isVerify()，参数是=JwtToken=。返回值是这个JwtToken是否存在
 *
 * @author gradylo
 * @date 2020/9/29 11:21 上午
 */
public class JwtUtil {

    private JwtUtil() {
    }

    /**
     * 创建默认的秘钥和算法，供无参的构造方法使用
     */
    private static final String DEFAULT_BASE64_ENCODED_SECRET_KEY = "BB436B2DC0E9D2B9F4ACB764A808BE0D";
    private static final SignatureAlgorithm DEFAULT_SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private static final String BASE_64_ENCODED_SECRET_KEY = Base64.encodeBase64String(
        DEFAULT_BASE64_ENCODED_SECRET_KEY.getBytes());
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = DEFAULT_SIGNATURE_ALGORITHM;


    /**
     * 这里就是产生jwt字符串的地方 jwt字符串包括三个部分 1. header -当前字符串的类型，一般都是“JWT” -哪种算法加密，“HS256”或者其他的加密算法
     * 所以一般都是固定的，没有什么变化 2. payload 一般有四个最常见的标准字段（下面有） iat：签发时间，也就是这个jwt什么时候生成的 jti：JWT的唯一标识
     * iss：签发人，一般都是username或者userId exp：过期时间
     *
     * @param iss       签发人，也就是JWT是给谁的（逻辑上一般都是username或者userId）
     * @param ttlMillis 生存时间,毫秒
     * @param claims    是指还想要在jwt中存储的一些非隐私信息
     * @return token
     */
    public static String encode(String iss, long ttlMillis, Map<String, Object> claims) {
        if (claims == null) {
            claims = new HashMap<>(16);
        }
        long nowMillis = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
            .setClaims(claims)
            //1. 这个地方就是以毫秒为单位，换算当前系统时间生成的iat
            .setIssuedAt(new Date(nowMillis))
            //2. 这个是JWT的唯一标识，一般设置成唯一的，这个方法可以生成唯一标识
            .setId(UUID.randomUUID().toString())
            //3. 签发人，也就是JWT是给谁的（逻辑上一般都是username或者userId）
            .setSubject(iss)
            //这个地方是生成jwt使用的算法和秘钥
            .signWith(SIGNATURE_ALGORITHM, BASE_64_ENCODED_SECRET_KEY);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            //4. 过期时间，这个也是使用毫秒生成的，使用当前时间+前面传入的持续时间生成
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 相当于encode的方向，传入jwtToken生成对应的username和password等字段。Claim就是一个map 也就是拿到荷载部分所有的键值对
     *
     * @param token 需要验证的Token
     * @return jwt中存储的一些非隐私信息
     */
    public static Claims decode(String token) {
        // 得到 DefaultJwtParser
        return Jwts.parser()
            // 设置签名的秘钥
            .setSigningKey(BASE_64_ENCODED_SECRET_KEY)
            // 设置需要解析的 jwt
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * 判断jwtToken是否合法
     *
     * @param token 需要验证的Token
     * @return jwt中存储的一些非隐私信息
     */
    public static boolean isVerify(String token) {
        //这个是官方的校验规则，这里只写了一个”校验算法“，可以自己加
        Algorithm algorithm;
        if (SIGNATURE_ALGORITHM == SignatureAlgorithm.HS256) {
            algorithm = Algorithm.HMAC256(Base64.decodeBase64(BASE_64_ENCODED_SECRET_KEY));
        } else {
            throw new RuntimeException("不支持该算法");
        }
        JWTVerifier verifier = JWT.require(algorithm).build();
        //判断合法的标准：1. 头部和荷载部分没有篡改过。2. 没有过期
        // 校验不通过会抛出异常
        verifier.verify(token);
        return true;
    }


}
