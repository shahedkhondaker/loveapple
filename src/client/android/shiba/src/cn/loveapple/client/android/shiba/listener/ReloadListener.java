package cn.loveapple.client.android.shiba.listener;

import cn.loveapple.client.android.shiba.ShibaFacadeActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

public class ReloadListener implements OnClickListener {
	private WebView webView;
	
	public ReloadListener(WebView webView){
		this.webView = webView;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onClick(View view) {
		webView.reload();
		((ShibaFacadeActivity)view.getContext()).setButtonEnabled();
	}
}
