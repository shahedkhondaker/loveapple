package cn.loveapple.client.android.shiba;

import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import cn.loveapple.client.android.bbt.view.LoveappleWebViewClient;
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
	/**
	 * WEBビュー
	 */
	private WebView webView;
    
    /**
	 * 初期化を行う
	 */
    @Override
	protected void init(){
    	requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	}
    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView(){
    	
    	webView = (WebView) findViewById(R.id.web);
    	AutoCompleteTextView edit = (AutoCompleteTextView) findViewById(R.id.UriText);
    	edit.setWidth(this.getWindowManager().getDefaultDisplay().getWidth()/2);
    	if(StringUtils.isNotEmpty(webView.getUrl())){
    		edit.setText(webView.getUrl());
    	}else{
    		edit.setText("");
    	}
		
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		final Button button = (Button) findViewById(R.id.GoButton);
		final OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO http://d.hatena.ne.jp/sardine/20061017
				final AutoCompleteTextView edit = (AutoCompleteTextView) findViewById(R.id.UriText);
				final String uriString = edit.getText().toString();
				webView.loadUrl(uriString);
				webView.requestFocus();
			}
		};
		button.setOnClickListener(listener);

		webView.setWebViewClient(new LoveappleWebViewClient(this));
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