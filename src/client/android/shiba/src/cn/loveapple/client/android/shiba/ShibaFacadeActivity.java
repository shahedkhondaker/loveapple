package cn.loveapple.client.android.shiba;

import java.util.List;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.View.OnKeyListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import cn.loveapple.client.android.bbt.view.LoveappleWebViewClient;
import cn.loveapple.client.android.shiba.database.CacheDao;
import cn.loveapple.client.android.shiba.database.impl.CacheDaoImpl;
import cn.loveapple.client.android.shiba.listener.BackListener;
import cn.loveapple.client.android.shiba.listener.ForwardListener;
import cn.loveapple.client.android.shiba.listener.ReloadListener;
import cn.loveapple.client.android.shiba.listener.RequestListener;
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
	/**
	 * WEBビュー
	 */
	private WebView webView;

	public static final int VIEW_URL_LIMIT = 100;

	/**
	 * 初期化を行う
	 */
	@Override
	protected void init() {
		cacheDao = new CacheDaoImpl();
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initView() {

		webView = (WebView) findViewById(R.id.web);

		// アドレスバーを初期化
		List<String> urlList = cacheDao.findCacheHttpUrl(null, VIEW_URL_LIMIT);
		AutoCompleteTextView address = (AutoCompleteTextView) findViewById(R.id.address);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_address, urlList);
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
			webView.loadUrl(url);
		}
		final OnKeyListener listener = new RequestListener(webView, address);
		address.setOnKeyListener(listener);

		webView.setWebViewClient(new LoveappleWebViewClient(this));

		// ボタンの初期化
		back = (ImageButton) findViewById(R.id.back);
		back.setOnClickListener(new BackListener(webView));
		forward = (ImageButton) findViewById(R.id.forward);
		forward.setOnClickListener(new ForwardListener(webView));
		refresh = (ImageButton) findViewById(R.id.refresh);
		refresh.setOnClickListener(new ReloadListener(webView));
		setButtonEnabled();
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
/*
	*//**
	 * TODO FLASH
	 * @param name
	 *//*
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
*/
	/**
	 * TODO Flash
	 */
	@Override
	protected void onPause() {
		super.onPause();
	}

	/**
	 * TODO Flash
	 */
	@Override
	protected void onResume() {
		super.onResume();
//		getWindow().setBackgroundDrawable(null);
//		WindowManager.LayoutParams lp = 
//			(WindowManager.LayoutParams) getWindow().getDecorView().getLayoutParams();
//	    lp.type = WindowManager.LayoutParams.TYPE_APPLICATION;
//	    getWindowManager().removeView(getWindow().getDecorView());
//	    getWindowManager().addView(getWindow().getDecorView(), lp);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	/**
	 * プロキシーを取得
	 * @return
	 */
	public String getProxyUrl(){
		return "http://atgapps.appspot.com/";
	}
}