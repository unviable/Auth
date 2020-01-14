package com.huawei.commons.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTUtil {
	public static final String SECRET_KEY = "abc123456"; 	//秘钥  
    public static final long TOKEN_EXPIRE_TIME = 5 * 60 * 1000; //token过期时间  
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 10 * 60 * 1000; //refreshToken过期时间  
    private static final String ISSUER = "xiangwei"; 		//签发人  
  
    /** 
     * 生成签名 
     */  
    public static String generateToken(String uname){  
        Date now = new Date();  
        //创建签名算法对象
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //算法  
          
        String token = JWT.create()  
            .withIssuer(ISSUER) //签发人  
            .withIssuedAt(now)  //签发时间  
            .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME)) //过期时间  
            .withClaim("uname", uname) //保存身份标识  
            .sign(algorithm);  
        return token;  
    }  
      
    /** 
     * 验证token 
     */  
    public static boolean verify(String token){  
        try {  
        	//签名算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //算法  
            JWTVerifier verifier = JWT.require(algorithm)  
                    .withIssuer(ISSUER)
                    .build();  
            verifier.verify(token);  
            return true;  
        } catch (Exception ex){  
            ex.printStackTrace();  
        }  
        return false;  
    }  
      
    /** 
     * 从token获取uname 
     */  
    public static String getUname(String token){  
        try{  
            return JWT.decode(token).getClaim("uname").asString();  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return "";  
    }  
}
