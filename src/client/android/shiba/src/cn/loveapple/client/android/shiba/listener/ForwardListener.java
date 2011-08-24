package cn.loveapple.client.android.shiba.listener;

import cn.loveapple.client.android.shiba.ShibaFacadeActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

public class ForwardListener implements OnClickListener {
	private WebView webView;
	
	public ForwardListener(WebView webView){
		this.webView = webView;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onClick(View view) {
		if(webView.canGoForward()){
    		webView.goForward();
    	}
		((ShibaFacadeActivity)view.getContext()).setButtonEnabled();
	}
}
