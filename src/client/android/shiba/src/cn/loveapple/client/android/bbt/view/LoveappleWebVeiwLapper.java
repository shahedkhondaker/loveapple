
package cn.loveapple.client.android.bbt.view;


import static cn.loveapple.client.android.Constant.LOG_TAG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * {@linkplain WebView}のラッパークラス
 * 
 * @author loveapple
 *
 */
@Deprecated 
public class LoveappleWebVeiwLapper{
	
	private static final Date TEST_FIX_TIME = new Date(2012, 6, 12);
	
	// TODO
	private static final boolean isProxy = true;
	
	private Proxy proxy;


	/**
	 * 対象となる{@linkplain WebView}インスタンス
	 */
	private WebView webView;
	
	/**
	 * 対象とする{@linkplain WebView}を用いて新しいインスタンスを生成
	 * @param webView
	 */
	public LoveappleWebVeiwLapper(WebView webView){
		this.webView = webView;
		proxy = getProxy();
	}

	/**
	 * @return the webView
	 */
	public WebView getWebView() {
		return webView;
	}
	
	/**
	 * {@linkplain WebView#loadData(String, String, String)}のラッパーメソッド
	 * @param data
	 * @param mimeType
	 * @param encoding
	 */
	public void loadData(String data, String mimeType, String encoding){
		webView.loadData(data, mimeType, encoding);
	}
	
	/**
	 * {@linkplain WebView#loadDataWithBaseURL(String, String, String, String, String)}のラッパーメソッド
	 * @param baseUrl
	 * @param data
	 * @param mimeType
	 * @param encoding
	 * @param historyUrl
	 */
	public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl){
		webView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
	}
	
	/**
	 * {@linkplain WebView#loadUrl(String)}のラッパーメソッド
	 * @param url
	 */
	public void loadUrl(String url){
		sendRequestFacade(webView, url);
		//webView.loadUrl(url);
	}
	
	/**
	 * {@linkplain WebView#loadUrl(String, Map)}のラッパーメソッド
	 * @param url
	 * @param additionalHttpHeaders
	 */
	public void loadUrl(String url, Map<String, String> additionalHttpHeaders){
		webView.loadUrl(url, additionalHttpHeaders);
	}
	
	/**
	 * {@linkplain WebView#setWebViewClient(WebViewClient)}のラッパーメソッド
	 * @param client
	 */
	public void setWebViewClient(WebViewClient client){
		webView.setWebViewClient(client);
	}



	/**
	 * 
	 * @param url
	 */
	private void sendRequestFacade(WebView view, String url){
		Date now = Calendar.getInstance().getTime();
		if(now.after(TEST_FIX_TIME)){
			Log.d(LOG_TAG, "test time is over." + TEST_FIX_TIME);
			String summary = "<html><body><H1>The test time is over!</H1></body></html>";
			view.loadData(summary, "text/html", null);
			return ;
		}
		if(isProxy){
			sendRequestBySocket(view, url);
		}else{
			sendRequestByWebview(view, url);
		}
	}
	
	/**
	 * webviewでHTTPリクエスト送信して、内容を画面に表示させる。
	 * 
	 * @param url リクエストURL
	 */
	private void sendRequestByWebview(WebView view, String url){
		view.loadUrl(url);
	}
	
	/**
	 * ソケット通信を利用して、HTTPリクエストを送信して、内容を画面に表示させる。
	 * 
	 * @param view
	 * @param urlStr
	 */
	private void sendRequestBySocket(WebView view, String urlStr){
		HttpURLConnection connection = null;
		BufferedReader br = null;
		try {
			URL url = new URL(urlStr);
			if (proxy != null) {
				connection = (HttpURLConnection) url.openConnection(proxy);
			} else {
				connection = (HttpURLConnection) url.openConnection();
			}
			connection.connect();

			br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = null;
			StringBuilder builder = new StringBuilder(2046);
			while((line = br.readLine())!=null){
				builder.append(line);
			}
			view.loadData(builder.toString(),
					connection.getContentType(),
					connection.getContentEncoding());
		} catch (MalformedURLException e) {
			Log.e(LOG_TAG, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(LOG_TAG, e.getMessage(), e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if(br != null){
				try {
					br.close();
				} catch (Exception e) {}
			}
		}
//		String summary = "<html><body>You scored <b>192</b> points.</body></html>";
//		view.loadData(summary, "text/html", null);
	}
	
	/**
	 * 
	 * @return
	 */
	private static Proxy getProxy() {
	    // (未設定時は、host=null、port=-1)
	    String host = android.net.Proxy.getDefaultHost();
	    int port = android.net.Proxy.getDefaultPort();
	    if ((host != null) && (port != -1)) {
	        SocketAddress addr = new InetSocketAddress(host, port);
	        return new Proxy(Proxy.Type.HTTP, addr);
	    } else {
	        return null;
	    }
	}
}
