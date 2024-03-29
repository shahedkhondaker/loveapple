package cn.loveapple.client.android.shiba;

import static cn.loveapple.client.android.util.DateUtil.*;
import static cn.loveapple.client.android.Constant.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import cn.loveapple.client.android.shiba.database.CacheDao;
import cn.loveapple.client.android.shiba.database.impl.CacheDaoImpl;
import cn.loveapple.client.android.shiba.listener.BackListener;
import cn.loveapple.client.android.shiba.listener.ForwardListener;
import cn.loveapple.client.android.shiba.listener.ReloadListener;
import cn.loveapple.client.android.shiba.listener.RequestListener;
import cn.loveapple.client.android.shiba.service.RsyncProxyServerListService;
import cn.loveapple.client.android.shiba.view.BannerTimerTask;
import cn.loveapple.client.android.shiba.view.LoveappleWebView;
import cn.loveapple.client.android.shiba.view.LoveappleWebViewClient;
import cn.loveapple.client.android.shiba.view.RsyncProxyServerListTimerTask;
import cn.loveapple.client.android.util.StringUtils;

/**
 * 柴犬ブラウザファーサード
 * 
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id: ShibaFacadeActivity.java 279 2011-08-25 05:00:11Z hao0323@gmail.com
 *     $
 * 
 */
public class ShibaFacadeActivity extends BaseActivity {
	private CacheDao cacheDao;
	private ImageButton back;
	private ImageButton forward;
	private ImageButton refresh;
	private Timer bannerTimer;
	private Timer rsyncTimer;
	
	/**
	 * {@linkplain WebView}のラッパークラス
	 */
	private LoveappleWebView webView;
	
	/**
	 * URL補完入力表示限界値
	 */
	public static final int VIEW_URL_LIMIT = 100;

	/**
	 * 初期化を行う
	 */
	@Override
	protected void init() {
		
		cacheDao = new CacheDaoImpl();
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		//Timer
		bannerTimer = new Timer();
		bannerTimer.schedule(new BannerTimerTask(this), 10000, 10000);
		
		//サーバリスト同期
		rsyncTimer = new Timer();
		rsyncTimer.schedule(new RsyncProxyServerListTimerTask(this), 3600000);
		
		// サービス初期化
		initService();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initView() {

		webView = (LoveappleWebView) findViewById(R.id.web);

		// アドレスバーを初期化
		AutoCompleteTextView address = (AutoCompleteTextView) findViewById(R.id.address);
		UrlAdapter adapter = new UrlAdapter(this, R.layout.list_address, cacheDao);
		address.setAdapter(adapter);
		address
				.setWidth(this.getWindowManager().getDefaultDisplay()
						.getWidth() / 2);
		address.setCompletionHint(getResources().getText(
				R.string.choose_address));
		address.setThreshold(0);

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setAllowFileAccess(true);
		webSettings.setPluginState(WebSettings.PluginState.ON);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setLightTouchEnabled(true);
		webSettings.setLoadsImagesAutomatically(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setGeolocationEnabled(true);
		webSettings.setLightTouchEnabled(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setSupportMultipleWindows(true);
		
		// FLASH
		webView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
		
		

		/*
		 * 初期には開始ページに設定 if(urlList != null && 0 < urlList.size()){
		 * webView.loadUrl(urlList.get(0)); address.setText(urlList.get(0));
		 * webView.requestFocus(); }
		 */
		String url = address.getText().toString();
		if(StringUtils.isEmpty(url)){
			webView.loadUrl("http://loveapple-facade.appspot.com/shiba.html");
		}else{
			webView.loadUrl(StringUtils.getUrlWhitchSchema(url));
		}
		final OnKeyListener listener = new RequestListener(webView, address);
		address.setOnKeyListener(listener);

		LoveappleWebViewClient client = new LoveappleWebViewClient(this);
		client.setHostWhiteList(getResources().getStringArray(R.array.host_white_list));
		client.setSchemaWhiteList(getResources().getStringArray(R.array.schema_white_list));
		webView.setWebViewClient(client);

		// ボタンの初期化
		back = (ImageButton) findViewById(R.id.back);
		back.setOnClickListener(new BackListener(webView));
		forward = (ImageButton) findViewById(R.id.forward);
		forward.setOnClickListener(new ForwardListener(webView));
		refresh = (ImageButton) findViewById(R.id.refresh);
		refresh.setOnClickListener(new ReloadListener(webView));
		setButtonEnabled();
		
		// テスト期間オーバの制御
		if(isTestTimeOver()){
			setTitle(getText(R.string.timeOver4Test));
		}else{
			setTitle("");
		}
		
	}

	public void setButtonEnabled() {
		back.setEnabled(webView.canGoBack());
		forward.setEnabled(webView.canGoForward());
		refresh.setEnabled(StringUtils.isNotEmpty(webView.getUrl()));
	}

	/**
	 * ブラウザバック
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		// For Flash Start
		webView.pauseTimers();
		if (isFinishing()) {
			webView.loadUrl("about:blank");
			setContentView(new FrameLayout(this));
		}
		callHiddenWebViewMethod("onPause");
		// For Flash End
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		// For Flash Start
		callHiddenWebViewMethod("onResume");
		// For Flash End
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	/**
	 * HTTPプロキシーを取得
	 * 
	 * @return
	 */
	public String getHttpProxyUrl(){
		return ShibaSetting.getHttpProxyServerHost(this);
	}
	/**
	 * プロキシーを取得
	 * 
	 * @return
	 */
	public String getProxyUrl(){
		return ShibaSetting.getProxyServerHost(this);
	}
	
	/**
	 * Flash を制御
	 * @param name
	 */
	private void callHiddenWebViewMethod(String name) {
		if (webView != null) {
			try {
				Method method = WebView.class.getMethod(name);
				method.invoke(webView);
			} catch (NoSuchMethodException e) {
				Log.i("No such method: " + name, e.toString());
			} catch (IllegalAccessException e) {
				Log.i("Illegal Access: " + name, e.toString());
			} catch (InvocationTargetException e) {
				Log.d("Invocation Target Exception: " + name, e.toString());
			}
		}
	}
	
	/**
	 * サービスの初期化を行う
	 * 
	 * @see RsyncProxyServerListService
	 */
	protected void initService() {
		Log.d(LOG_TAG, "init service");
		// プロキシサーバリスト同期サービス
		Intent rsyncProxyServerListServiceIntent = new Intent(this, RsyncProxyServerListService.class);
		ComponentName compName = startService(rsyncProxyServerListServiceIntent);// TODO bindService?
		Log.d(LOG_TAG, "init service over." + compName);
	}
}