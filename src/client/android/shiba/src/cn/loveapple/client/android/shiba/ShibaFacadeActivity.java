package cn.loveapple.client.android.shiba;

import java.util.List;

import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.view.Window;
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
 * @id $Id$
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
	protected void init(){
    	cacheDao = new CacheDaoImpl();
    	requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	}
    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView(){
    	
    	webView = (WebView) findViewById(R.id.web);
    	
    	// アドレスバーを初期化
    	List<String> urlList = cacheDao.findCacheHttpUrl(null, VIEW_URL_LIMIT);
    	AutoCompleteTextView address = (AutoCompleteTextView) findViewById(R.id.address);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_address, urlList);
		address.setAdapter(adapter);
    	address.setWidth(this.getWindowManager().getDefaultDisplay().getWidth()/2);
    	address.setCompletionHint(getResources().getText(R.string.choose_address));
    	address.setThreshold(0);
    	
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);

    	/*
    	 * 初期には開始ページに設定
    	 * if(urlList != null && 0 < urlList.size()){
    		webView.loadUrl(urlList.get(0));
    		address.setText(urlList.get(0));
    		webView.requestFocus();
    	}*/
		webView.loadUrl("http://loveapple-facade.appspot.com/shiba.html");
		final OnKeyListener listener = new RequestListener(webView, (AutoCompleteTextView) findViewById(R.id.address));
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
    
    
    public void setButtonEnabled(){
		back.setEnabled(webView.canGoBack());
		forward.setEnabled(webView.canGoForward());
		refresh.setEnabled(StringUtils.isNotEmpty(webView.getUrl()));
    }
    /**
     * ブラウザバック
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
    	if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
    		webView.goBack();
    		return true;
    	}
    	
    	return super.onKeyDown(keyCode, event);
    }
}