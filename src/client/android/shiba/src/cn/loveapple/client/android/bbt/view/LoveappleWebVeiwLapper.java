
package cn.loveapple.client.android.bbt.view;

import java.util.Map;

import android.webkit.WebView;

/**
 * {@linkplain WebView}のラッパークラス
 * 
 * @author loveapple
 *
 */
public class LoveappleWebVeiwLapper{


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
		webView.loadUrl(url);
	}
	
	/**
	 * {@linkplain WebView#loadUrl(String, Map)}のラッパーメソッド
	 * @param url
	 * @param additionalHttpHeaders
	 */
	public void loadUrl(String url, Map<String, String> additionalHttpHeaders){
		webView.loadUrl(url, additionalHttpHeaders);
	}

}
