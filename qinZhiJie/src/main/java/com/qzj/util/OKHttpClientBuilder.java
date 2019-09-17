package com.qzj.util;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
 
public class OKHttpClientBuilder {
    public static OkHttpClient.Builder buildOKHttpClient() {
        try {
            TrustManager[] trustAllCerts = buildTrustManagers();
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
 
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
            return builder;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
            return new OkHttpClient.Builder();
        }
    }
 
    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }
 
                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }
 
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
    }
    
    public static void main(String[] args) {
//		try {
//			String appId = "wxa163a5a1bddc2c89";
//			String secret = "f1758fbe926b4d976a58ecd658b65574";
//			String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+secret+"&js_code="+str+"&grant_type=authorization_code";
//			OkHttpClient client = new OkHttpClient();
//			Request request = new Request.Builder()
//			  .url(url)
//			  .get()
//			  .addHeader("Content-Type", "application/json")
//			  .build();
//			Response response = client.newCall(request).execute();
//			  System.out.println(response.body().string());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//        String url = "https://www.baidu.com";
//        String json = "{}";
//        Request request = new Request.Builder()
//                .url(url)
//                .headers(Headers.of(new HashMap<>()))
//                .post(RequestBody.create(JSON, json))
//                .build();
//        OKHttpClientBuilder.buildOKHttpClient()
//                .build()
//                .newCall(request)
//                .enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        System.out.println("onFailure()");
//                        e.printStackTrace();
//                    }
// 
//                    @Override
//                    public void onResponse(Call call, Response response) {
//                    	System.out.println(response);
//                        System.out.println("onResponse()");
//                    }
//                });
    }
}