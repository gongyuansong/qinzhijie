package com.qzj.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.qzj.commos.vo.ResponseData;
import com.qzj.dto.WeiXinUser;
import com.qzj.service.LoginService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController extends BaseTgController {

	private static final Gson gson = new Gson();	
	
	@Autowired
	private LoginService service;
	 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getOpenId", method = RequestMethod.POST)
	public ResponseData<Map<String, String>> getOpenId(@RequestParam(value = "jsCode") String str, HttpServletRequest request) {
		ResponseData<Map<String, String>> result = new ResponseData<Map<String, String>>();
		try {
			String appId = "wxa163a5a1bddc2c89";
			String secret = "f1758fbe926b4d976a58ecd658b65574";
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret
					+ "&js_code=" + str + "&grant_type=authorization_code";
			OkHttpClient client = new OkHttpClient();
			Request req = new Request.Builder().url(url).get().addHeader("Content-Type", "application/json")
					.build();
			Response response = client.newCall(req).execute();
			//查看数据库表中有没有这个openId,如果有表示，登录过，如果没有就需要登录
//			if(登录过) {
//				看看session 是否过期
//				如果没过期，就直接放过
//				如果过期就跳转到重新登陆页面
//			}else {
//				跳转到登陆页面授权
//			}
			Map<String, String> m = gson.fromJson(response.body().string(), Map.class);
			result.setData(m);
			result.setCode("200");
			result.setMessage("success");
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		result.setCode("200");
		result.setMessage("error");
		return result;
	 }
	
	@RequestMapping(value = "/loginIn", method = RequestMethod.POST)
	public ResponseData<String> loginIn(@RequestParam(value = "openId") String openId, HttpServletRequest request) {
		ResponseData<String> result = new ResponseData<String>();
		try {
			List<WeiXinUser> list = service.getByOpenId(openId);
			if(list != null && list.size() != 0) {
				service.update(list.get(0));
			}else {
				service.add(openId);
			}
			result.setData("");
			result.setCode("200");
			result.setMessage("success");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.setCode("200");
		result.setMessage("error");
		return result;
	 }
	
	@RequestMapping(value = "/isLogin", method = RequestMethod.POST)
	public ResponseData<String> isLogin(@RequestParam(value = "openId") String openId, HttpServletRequest request) {
		ResponseData<String> result = new ResponseData<String>();
		try {

			result.setData("success");
			result.setCode("200");
			result.setMessage("success");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.setCode("200");
		result.setMessage("error");
		return result;
	 }
	
//	@RequestMapping(value = "/getuserInfo", method = RequestMethod.POST)
//	public ResponseData<String> getuserInfo(@RequestParam(value = "jsCode") String str, HttpServletRequest request) {
//		
//		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
//		AccessToken accessToken = WeiXinUtils.getAccessToken();        //获取access_token        
//		String token = accessToken.getToken();        
//		String url = GET_USERINFO_URL.replace("ACCESS_TOKEN" , token);        
//		url = url.replace("OPENID" ,opendID);        
//		JSONObject jsonObject = WeiXinUtils.doGetStr(url);        
//		return jsonObject.toString();
//		
//		
//		
//		return null;
//	}
	
}
