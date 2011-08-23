package cn.loveapple.client.android.shiba;

import java.util.Date;

import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import cn.loveapple.client.android.bbt.view.LoveappleWebViewClient;
import cn.loveapple.client.android.shiba.database.entity.BookMarkEntity;
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
    	AutoCompleteTextView address = (AutoCompleteTextView) findViewById(R.id.address);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_address, new String[]{"a","b"});
		address.setAdapter(adapter);
    	address.setWidth(this.getWindowManager().getDefaultDisplay().getWidth()/2);
    	address.setCompletionHint("choose address");
    	address.setThreshold(0);
    	
    	if(StringUtils.isNotEmpty(webView.getUrl())){
    		address.setText(webView.getUrl());
    	}else{
    		address.setText("");
    	}
		
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		final Button button = (Button) findViewById(R.id.GoButton);
		final OnClickListener listener = new RequestListener(webView, (AutoCompleteTextView) findViewById(R.id.address));
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