package com.huawei.authcenter.handler;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huawei.commons.pojo.User;
import com.huawei.commons.utils.AuthResult;
import com.huawei.commons.utils.JWTUtil;
import com.huawei.commons.utils.RedisUtil;

@RestController
public class UserHandler {
	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping("/login")
	public AuthResult login(User user) {
		//假设用户认证成功   subject.login(token)
		//生成token，将token返回给前端浏览器
		String token = JWTUtil.generateToken("zhangsan");	//要保存的token
		String refreshToken = UUID.randomUUID().toString();	//key
		
		//将token  refreshtoken都存放到redis中
		redisUtil.hset(refreshToken, "token", token);
		redisUtil.hset(refreshToken, "uname", "zhangsan");
		redisUtil.expire(refreshToken, JWTUtil.REFRESH_TOKEN_EXPIRE_TIME);
		
		//将refreshToken  token都返回给浏览器
		AuthResult authResult = new AuthResult();
		authResult.setToken(token);
		authResult.setRefreshToken(refreshToken);
		authResult.setState(AuthResult.STATE_SUCCESS);
		return authResult;
	}
	/*
	 * 刷新token
	 */
	@RequestMapping("/refresh/{refreshToken}")
	public AuthResult refresh(@PathVariable("refreshToken") String refreshToken) {
		//判断refreshToken是否过期
		Object result = redisUtil.get(refreshToken);
		if (result==null) {
			//重新登录.........
			return null;
		}
		String token = (String)redisUtil.hget(refreshToken, "token");
		if (token==null) {
			token = JWTUtil.generateToken((String)redisUtil.hget(refreshToken, "uname"));
			//存放到redis中
			redisUtil.hset(refreshToken, "token", token);
		}
		AuthResult authResult = new AuthResult();
		authResult.setRefreshToken(refreshToken);
		authResult.setToken(token);
		authResult.setState(AuthResult.STATE_SUCCESS);
		return authResult;
	}
	/*
	 * 判断是否有指定角色方法
	 * 
	 */
	@RequestMapping("/hasRole/{token}/{role}")
	public boolean hasRole(@PathVariable("token") String token,@PathVariable("role") String role) {
		//获取账号
		String uname = JWTUtil.getUname(token);
		//根据账号到数据库中去查询角色
		String resultRole = "student";
		//比较角色
		if (resultRole.equals(role)) {
			return true;
		}
		return false;
	}
}
