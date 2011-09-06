package cn.loveapple.client.android.util;

import static cn.loveapple.client.android.Constant.*;

import org.apache.http.client.protocol.ClientContext;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import cn.loveapple.client.android.damtomo.service.HttpService;

import android.net.http.AndroidHttpClient;

public class HttpClientUtil {
	private static class HttpClientHolder{
		public static AndroidHttpClient httpClient;
		{
			httpClient = AndroidHttpClient.newInstance(HttpService.HTTP_USER_AGENT);
		}
	}
	
	public static AndroidHttpClient getHttpClient(){
		return HttpClientHolder.httpClient;
	}
}
