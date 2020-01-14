package com.huawei.commons.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResult {
	public static final int STATE_OLD = 0;
	public static final int STATE_SUCCESS = 1001;
	public static final int STATE_FAILURE = 1002;
	
	private int state;				//状态
	private String message;			//消息
	private String token;			//token
	private String refreshToken;	//refreshToken
	public AuthResult(int state,String message){
		this.state = state;
		this.message = message;
	}
}
